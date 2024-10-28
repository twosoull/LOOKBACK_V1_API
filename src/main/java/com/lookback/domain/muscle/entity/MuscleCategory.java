package com.lookback.domain.muscle.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MUSCLE_CATEGORY")
public class MuscleCategory {

    @Id @GeneratedValue
    @Column(name = "MUSCLE_CATEGORY_ID")
    private Long id;

    private String muscleCategoryName;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // 기본 생성자
    protected MuscleCategory() {}

    // Excel 데이터를 위한 생성자
    public MuscleCategory(String muscleCategoryName, String description) {
        this.muscleCategoryName = muscleCategoryName;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "admin";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "admin";
    }
}
