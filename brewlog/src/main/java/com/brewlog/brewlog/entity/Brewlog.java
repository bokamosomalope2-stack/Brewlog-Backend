package com.brewlog.brewlog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Brews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brewlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String beans;

    @Column(nullable = false)
    private String method;

    @Column(name = "coffee_grams", nullable = false)
    private Integer coffeeGrams;

    @Column(name = "water_grams", nullable = false)
    private Integer waterGrams;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "testing_notes", nullable = false)
    private String testingNotes;
}
