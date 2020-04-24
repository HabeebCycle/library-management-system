package com.habeebcycle.lms.service.userservice.database;

import com.habeebcycle.lms.service.userservice.model.User;
import com.habeebcycle.lms.service.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@DataJpaTest
@Transactional(propagation = NOT_SUPPORTED)
public class PersistenceTests {

    @Autowired
    private UserRepository userRepository;

    private User savedUser;

    @BeforeEach
    public void setupDb() {
        userRepository.deleteAll();

        User user = new User("Habeeb", "Okunade", "habeebCycle", "habeeb@habeeb.com", "habeeb");
        savedUser = userRepository.save(user);

        assertEqualUser(user, savedUser);
    }

    @Test
    public void create() {

        User user = new User("Habeeb1", "Okunade1", "habeebCycle1", "habeeb1@habeeb.com", "habeeb1");
        User newUser = userRepository.save(user);

        User foundUser = userRepository.findById(newUser.getId()).get();
        assertEqualUser(user, foundUser);

        assertEquals(2, userRepository.count());
    }

    @Test
    public void update() {
        savedUser.setPassword("updatedPassword");
        userRepository.save(savedUser);

        User foundUser = userRepository.findById(savedUser.getId()).get();
        assertEquals(1, userRepository.count());
        assertEquals("updatedPassword", foundUser.getPassword());
    }

    @Test
    public void delete() {
        userRepository.delete(savedUser);
        assertFalse(userRepository.existsByUsername(savedUser.getUsername()));
    }

    @Test
    public void getByUserId() {
        Optional<User> user = userRepository.findById(savedUser.getId());

        assertTrue(user.isPresent());
        assertEqualUser(savedUser, user.get());
    }


    private void assertEqualUser(User expectedUser, User actualUser) {
        assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(),  actualUser.getLastName());
        assertEquals(expectedUser.getUsername(),    actualUser.getUsername());
        assertEquals(expectedUser.getEmail(),   actualUser.getEmail());
        assertEquals(expectedUser.getPassword(),   actualUser.getPassword());
    }
}
