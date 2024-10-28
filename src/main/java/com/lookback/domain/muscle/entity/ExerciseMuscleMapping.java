package com.lookback.domain.muscle.entity;

import com.lookback.domain.exercise.entity.Exercise;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EXERCISE_MUSCLE_MAPPING")
public class ExerciseMuscleMapping {

    @Id @GeneratedValue
    @Column(name = "EMM_ID")
    private Long emmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIMARY_MUSCLE_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MuscleCategory primaryMuscleCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECOND_MUSCLE_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MuscleCategory secondMuscleCategory;

    private String role;
    private Long activationLevel;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    // 기본 생성자
    protected ExerciseMuscleMapping() {}

    // Excel 데이터를 위한 생성자
    public ExerciseMuscleMapping(Exercise exercise, MuscleCategory primaryMuscleCategory,
                                 MuscleCategory secondMuscleCategory, String role,
                                 Long activationLevel, String description) {
        this.exercise = exercise;
        this.primaryMuscleCategory = primaryMuscleCategory;
        this.secondMuscleCategory = secondMuscleCategory;
        this.role = role;
        this.activationLevel = activationLevel;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "admin";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "admin";
    }

}
