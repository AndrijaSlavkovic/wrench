package com.andrija.wrench.service;

import com.andrija.wrench.User;
import com.andrija.wrench.UserRepository;
import com.andrija.wrench.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean // Mock the UserRepository for controlled behavior
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() throws IllegalArgumentException {
        String username = "testuser";
        String password = "password";
        String email = "test@email.com";
        User.Role role = User.Role.USER;

        User savedUser = userService.registerUser(username, password, email, role);

        // Verify interaction with UserRepository (using Mockito here)
        verify(userRepository).save(any(User.class)); // Mockito assertion

        assertNotNull(savedUser);
        assertEquals(username, savedUser.getUsername()); // Assuming getters work
    }

    @Test(expected = IllegalArgumentException.class) // Expected exception for duplicate username
    public void testRegisterUserDuplicateUsername() throws IllegalArgumentException {
        String username = "testuser";
        String password = "password";
        String email = "test@email.com";
        User.Role role = User.Role.USER;

        User user1 = new User(username, password, email, role);
        userRepository.save(user1); // Mock UserRepository to return existing user

        userService.registerUser(username, password, email, role); // Should throw exception
    }

    // Add additional tests for other UserService methods (login, getUserById, etc.)
}
