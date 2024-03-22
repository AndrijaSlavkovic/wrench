package com.andrija.wrench;

import jakarta.persistence.*;

@Entity
public class User {

    public User() {
        // Empty constructor
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // Hashed password

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; // Optional: Define user roles (e.g., USER, PREMIUM_USER)

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public Role getRole() {
        return this.role;
    }

    public String getEmail() {
        return this.email;
    }

    public enum Role {
        USER,
        PREMIUM_USER
    }
    // Getters and Setters (omitted for brevity)

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password; // Assume password is already hashed
        this.email = email;
        this.role = role;
    }
}
