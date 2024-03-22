package com.andrija.wrench.repository;

import com.andrija.wrench.User;
import com.andrija.wrench.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll(); // Clear any existing data before each test
    }

    @Test
    public void testSaveUser() {
        User user = new User("testuser", "password", "test@email.com", User.Role.USER);
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("test@email.com", savedUser.getEmail()); // Assuming getters work
        assertEquals(User.Role.USER, savedUser.getRole()); // Assuming getters work
    }

    @Test
    public void testFindByUsername() {
        User user = new User("testuser", "password", "test@email.com", User.Role.USER);
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("testuser");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    public void testFindByUsernameNotFound() {
        User foundUser = userRepository.findByUsername("nonexistentuser");

        assertNull(foundUser);
    }

    // Add additional tests for other UserRepository methods (existsByUsername, etc.)
}

