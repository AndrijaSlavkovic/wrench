package com.andrija.wrench;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Password encoder (e.g., BCryptPasswordEncoder)
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String email, User.Role role) {
        User existingUserWithThisUsername = userRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(existingUserWithThisUsername)) {
            String encodedPassword = passwordEncoder.encode(password);
            User user = new User(username, encodedPassword, email, role);
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Username already exists!"); // Re-throw here
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Return null if user not found
    }

    public User updateUser(User user) {
        // Validation logic can be added here to ensure user updates are valid (e.g., unique email)
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Additional methods (consider implementing these based on your needs)
    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword()); // Check hashed password
        }
        return false; // User not found or password mismatch
    }
}

