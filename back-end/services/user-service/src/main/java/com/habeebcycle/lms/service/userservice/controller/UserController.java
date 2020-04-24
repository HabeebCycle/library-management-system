package com.habeebcycle.lms.service.userservice.controller;

import com.habeebcycle.lms.service.userservice.model.Role;
import com.habeebcycle.lms.service.userservice.model.RoleName;
import com.habeebcycle.lms.service.userservice.model.User;
import com.habeebcycle.lms.service.userservice.service.UserService;
import com.habeebcycle.lms.service.userservice.util.exception.BadRequestException;
import com.habeebcycle.lms.service.userservice.util.exception.InternalServerException;
import com.habeebcycle.lms.service.userservice.util.exception.NotFoundException;
import com.habeebcycle.lms.service.userservice.util.http.ServiceUtil;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping("/service/user")
public class UserController {

    private final UserService userService;
    private final ServiceUtil serviceUtil;
    private final Scheduler scheduler;

    @Autowired
    public UserController(UserService userService, ServiceUtil serviceUtil, Scheduler scheduler) {
        this.userService = userService;
        this.serviceUtil = serviceUtil;
        this.scheduler = scheduler;
    }

    @PostMapping
    public User registerUser(@Valid @RequestBody User user){
        //Creating user's account
        if(user.getRoles().isEmpty()) {
            Role userRole = userService.findRoleByRoleName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new InternalServerException("User Role not set."));
            user.setRoles(Collections.singleton(userRole));
        }

        return userService.saveOrUpdateUser(user);
    }

    @GetMapping("/{usernameOrEmail}")
    public Optional<User> checkUser(@PathVariable String usernameOrEmail){
        if(userService.userExistsByEmail(usernameOrEmail)){
            return userService.userByEmail(usernameOrEmail);
        }else if(userService.userExistsByUsername(usernameOrEmail)){
            return userService.userByUsername(usernameOrEmail);
        }else{
            return Optional.empty();
        }
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user){
        User oldUser = userService.getUserById(user.getId())
                .orElseThrow(() -> new NotFoundException("User Resources", "id", user.getId()));
        user.setRoles(oldUser.getRoles());
        return userService.saveOrUpdateUser(user);
    }

    @PutMapping("/add/role/{userId}/{role}")
    public User addUserRole(@PathVariable String userId, @PathVariable String role){
        Role newRole = userService.findRoleByRoleName(RoleName.valueOf(role))
                .orElseThrow(() -> new BadRequestException("Invalid Role Name."));
        User user = userService.getUserById(Long.parseLong(userId))
                .orElseThrow(() -> new NotFoundException("User Resources", "id", userId));
        user.getRoles().add(newRole);
        return userService.saveOrUpdateUser(user);
    }

    @PutMapping("/remove/role/{userId}/{role}")
    public User removeUserRole(@PathVariable String userId, @PathVariable String role){
        Role oldRole = userService.findRoleByRoleName(RoleName.valueOf(role))
                .orElseThrow(() -> new BadRequestException("Invalid Role Name."));
        User user = userService.getUserById(Long.parseLong(userId))
                .orElseThrow(() -> new NotFoundException("User Resources", "id", userId));
        user.getRoles().remove(oldRole);
        return userService.saveOrUpdateUser(user);
    }

    @GetMapping("/address")
    public String serverAddress(){
        return serviceUtil.getServiceAddress();
    }

    @GetMapping("/roles")
    public Flux<Role> getAllRoles(){
        return Flux.fromIterable(userService.findAllRole());
    }

    @GetMapping("role/{role}")
    public Flux<User> getUserByRole(@PathVariable String role){
        Role userRole = userService.findRoleByRoleName(RoleName.valueOf(role))
                .orElseThrow(() -> new BadRequestException("Invalid Role Name."));
        return asyncFlux(() -> Flux.fromIterable(userService.findAllUserWithRole(userRole)));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(Long.parseLong(userId));
    }

    private <T> Flux<T> asyncFlux(Supplier<Publisher<T>> publisherSupplier) {
        return Flux.defer(publisherSupplier).subscribeOn(scheduler);
    }

}
