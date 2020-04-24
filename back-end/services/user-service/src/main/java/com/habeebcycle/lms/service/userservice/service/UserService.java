package com.habeebcycle.lms.service.userservice.service;

import com.habeebcycle.lms.service.userservice.model.Role;
import com.habeebcycle.lms.service.userservice.model.RoleName;
import com.habeebcycle.lms.service.userservice.model.User;
import com.habeebcycle.lms.service.userservice.repository.RoleRepository;
import com.habeebcycle.lms.service.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<User> userByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> userByUsernameOrEmail(String username, String email){
        return userRepository.findByUsernameOrEmail(username, email);
    }

    public List<User> userByIdIn(List<Long> userIds){
        return userRepository.findByIdIn(userIds);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    private List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> userByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Boolean userExistsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public Boolean userExistsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User saveOrUpdateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public void deleteAllUser(){
        userRepository.deleteAll();
    }

    public Long getNumberOfUser(){
        return userRepository.count();
    }

    public Optional<Role> findRoleByRoleName(RoleName roleName){
        return roleRepository.findByName(roleName);
    }

    public List<Role> findAllRole(){
        return roleRepository.findAll();
    }

    public Role saveOrUpdateRole(Role role){
        return roleRepository.save(role);
    }

    public List<User> findAllUserWithRole(Role role){
        return getAllUsers().stream()
                .filter(user -> user.getRoles().contains(role))
                .collect(Collectors.toList());
    }
}
