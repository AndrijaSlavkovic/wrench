package com.andrija.wrench;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {

    public Car() {
        // empty
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private Integer year;
    private Long mileage; // Consider using a BigDecimal for precise mileage tracking

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // Car belongs to a User

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services; // List of services performed on this car

    // Getters and Setters (omitted for brevity)

    public Car(String model, Integer year, Long mileage, User user) {
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.user = user;
        this.services = new ArrayList<>(); // Initialize empty list for services
    }
}
