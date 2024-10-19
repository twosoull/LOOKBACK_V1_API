package com.lookback.domain.exercise.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "EXERCISE")
public class Exercise {

    @Id @GeneratedValue
    @Column(name = "EXERCISE_ID")
    private Long exerciseId;

    private String exerciseName;
    private String exerciseLevel;
    private String equipment;
    private Integer caloriesBurned;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
