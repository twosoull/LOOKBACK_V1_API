package com.lookback.domain.muscle.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MuscleGroup extends BaseEntity {

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

    // 기본 생성자
    protected MuscleGroup() {}

    // Excel 데이터를 위한 생성자
    public MuscleGroup(String muscleGroupName, String description, MuscleCategory muscleCategory) {
        this.muscleGroupName = muscleGroupName;
        this.description = description;
        this.muscleCategory = muscleCategory;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "admin";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "admin";
    }
}
