package com.andrija.wrench;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email); // Optional, based on your needs

    boolean existsByUsername(String username); // Check for username existence

    boolean existsByEmail(String email); // Optional, based on your needs

    List<User> findAllByRole(User.Role role); // Find all users with a specific role

    Optional<User> findById(Long id); // Optional return type for null handling
}

