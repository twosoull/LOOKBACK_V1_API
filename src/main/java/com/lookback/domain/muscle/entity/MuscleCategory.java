package com.lookback.domain.muscle.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MUSCLE_CATEGORY")
public class MuscleCategory {

    @Id @GeneratedValue
    @Column(name = "MUSCLE_CATEGORY_ID")
    private Long id;

    private String MuscleCategoryName;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
