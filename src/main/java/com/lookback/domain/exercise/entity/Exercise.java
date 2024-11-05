package com.lookback.domain.exercise.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.muscle.entity.Muscle;
import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "EXERCISE")
public class Exercise extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "EXERCISE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUSCLE_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MuscleCategory MuscleCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUSCLE_GROUP_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MuscleGroup muscleGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIME_MUSCLE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Muscle primeMuscle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUXILIARY_MUSCLE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Muscle AuxiliaryMuscle;

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
