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

}
