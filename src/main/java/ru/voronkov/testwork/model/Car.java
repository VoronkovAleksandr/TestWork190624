package ru.voronkov.testwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]+-[a-zA-Z0-9]+$", message = "Vendor-Model format should be 'vendor-model'")
    private String model;

    @Column(nullable = false)
    private Integer horsepower;

    @Column(nullable = false)
    private Long ownerId;
}
