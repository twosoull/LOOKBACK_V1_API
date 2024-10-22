package com.lookback.domain.muscle.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MuscleGroup {

    @Id @GeneratedValue
    @Column(name = "MUSCLE_GROUP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUSCLE_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MuscleCategory muscleCategory;

    private String muscleGroupName;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
