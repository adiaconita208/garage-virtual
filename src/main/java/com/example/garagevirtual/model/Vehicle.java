package com.example.garagevirtual.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String VIN;
    private String make;
    private String model;
    private int year;
    private String type;
    private boolean available;
}
