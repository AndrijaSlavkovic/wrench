package com.andrija.wrench;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Service {

    public Service() {
        // Empty constructor
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car; // Service is associated with a Car

    // Getters and Setters (omitted for brevity)

    public Service(Date date, String description, Double cost, Car car) {
        this.date = date;
        this.description = description;
        this.cost = cost;
        this.car = car;
    }
}