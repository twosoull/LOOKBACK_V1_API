package com.lookback.domain.exercise.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "EXERCISE")
public class Exercise {

    @Id
    @GeneratedValue
    @Column(name = "EXERCISE_ID")
    private Long id;

    private String exerciseName;
    private String exerciseLevel;
    private String equipment;
    private Integer caloriesBurned;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // 기본 생성자
    protected Exercise() {
    }

    // Excel 데이터를 위한 생성자
    public Exercise(String exerciseName, String exerciseLevel, String equipment,
                    Integer caloriesBurned, String description) {
        this.exerciseName = exerciseName;
        this.exerciseLevel = exerciseLevel;
        this.equipment = equipment;
        this.caloriesBurned = caloriesBurned;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "admin";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "admin";
    }
}
