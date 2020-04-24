package com.habeebcycle.lms.service.userservice;

import com.habeebcycle.lms.service.userservice.model.Role;
import com.habeebcycle.lms.service.userservice.model.User;
import com.habeebcycle.lms.service.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static reactor.core.publisher.Mono.just;

@SpringBootTest(webEnvironment=RANDOM_PORT,
        properties = {"logging.level.com.habeebcycle=DEBUG",
                "spring.datasource.url=jdbc:h2:mem:lms-user-service-db"})
class UserServiceApplicationTests {

    @Autowired
    private WebTestClient client;

    @Autowired
    private UserService userService;

    private User savedUser;

    @BeforeEach
    void setUpDB() {
        userService.deleteAllUser();
        User user = new User("Habeeb", "Okunade", "habeebCycle", "habeeb@habeeb.com", "habeeb");
        savedUser = postAndVerifyUser(user).getResponseBody();

        assertEquals(userService.getNumberOfUser(), 1);
    }

    @Test
    void postNewUser(){
        User user = new User("Habeeb2", "Okunade2", "habeebCycle2", "habeeb2@habeeb.com", "habeeb2");
        User savedUser = postAndVerifyUser(user).getResponseBody();
        User searchedUser = getUserEntity(user.getUsername()).getResponseBody();

        assertNotNull(searchedUser);
        assertEqualUser(savedUser, searchedUser);
        assertEquals(userService.getNumberOfUser(), 2);

    }

    @Test
    void updateAndVerifyUser(){
        savedUser.setFirstName("HabeebUpdate");
        User updatedUser = updateAndVerifyUser(savedUser).getResponseBody();
        User searchedUser = getUserEntity(savedUser.getUsername()).getResponseBody();

        assertEquals(1, userService.getNumberOfUser());
        assertEqualUser(updatedUser, searchedUser);
        assertEqualUser(savedUser, updatedUser);
        assertEquals(savedUser.getFirstName(), searchedUser.getFirstName());
    }

    @Test
    void setAndUpdateRoles(){
        Set<Role> userRoles = savedUser.getRoles();
        assertEquals(1, userRoles.size());

        User userWithRoles = getUserRoles(savedUser.getId().toString(), "ROLE_ADMIN", "add").getResponseBody();
        assertEquals(2, userWithRoles.getRoles().size());

        userWithRoles = getUserRoles(savedUser.getId().toString(), "ROLE_ADMIN", "remove").getResponseBody();
        assertEquals(1, userWithRoles.getRoles().size());

        userWithRoles = getUserRoles(savedUser.getId().toString(), "ROLE_USER", "remove").getResponseBody();
        assertEquals(0, userWithRoles.getRoles().size());

        userWithRoles = getUserRoles(savedUser.getId().toString(), "ROLE_USER", "add").getResponseBody();
        assertEquals(1, userWithRoles.getRoles().size());
    }

    @Test
    void getUserByEmailOrUsername(){
        User foundUser = getUserEntity(savedUser.getUsername()).getResponseBody();
        assertEqualUser(foundUser, savedUser);

        foundUser = getUserEntity("badUsername").getResponseBody();
        assertNull(foundUser);
    }

    @Test
    void duplicateError() {

        assertEquals(1, userService.getNumberOfUser());
        User clonedUser = new User("Habeeb", "Okunade", "habeebCycle", "habeeb@habeeb.com", "habeeb");

        assertThrows(DataIntegrityViolationException.class, () -> {
            userService.saveOrUpdateUser(clonedUser);
        });

        assertEquals(1, userService.getNumberOfUser());
    }

    @Test
    void deleteUser(){
        deleteAndVerifyUserById(savedUser.getId().toString());
        User foundUser = getUserEntity(savedUser.getUsername()).getResponseBody();

        assertNull(foundUser);
    }

    private EntityExchangeResult<User> getUserEntity(String usernameOrEmail){
        return client.get().uri("/service/user/" + usernameOrEmail)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(User.class)
                .returnResult();
    }

    private EntityExchangeResult<User> getUserRoles(String userId, String role, String operation){
        return client.put().uri("/service/user/" + operation + "/role/" + userId + "/" + role)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(User.class)
                .returnResult();
    }

    private EntityExchangeResult<User> postAndVerifyUser(User user) {
        return client.post()
                .uri("/service/user/")
                .body(just(user), User.class)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(User.class)
                .returnResult();
    }

    private EntityExchangeResult<User> updateAndVerifyUser(User user) {
        return client.put()
                .uri("/service/user/")
                .body(just(user), User.class)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(User.class)
                .returnResult();
    }

    private void deleteAndVerifyUserById(String userId) {
        client.delete()
                .uri("/service/user/" + userId)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    private void assertEqualUser(User expectedUser, User actualUser) {
        assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(),  actualUser.getLastName());
        assertEquals(expectedUser.getUsername(),    actualUser.getUsername());
        assertEquals(expectedUser.getEmail(),   actualUser.getEmail());
        assertEquals(expectedUser.getPassword(),   actualUser.getPassword());
    }

}
