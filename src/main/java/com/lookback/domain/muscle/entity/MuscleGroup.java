package com.lookback.domain.muscle.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.muscle.command.MuscleGroupCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
public class MuscleGroup extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MUSCLE_GROUP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUSCLE_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MuscleCategory muscleCategory;

    private String muscleGroupName;
    private String description;
    private String createdBy;
    private String updatedBy;

    // 기본 생성자
    protected MuscleGroup() {}

    // Excel 데이터를 위한 생성자
    public MuscleGroup(String muscleGroupName, String description, MuscleCategory muscleCategory) {
        this.muscleGroupName = muscleGroupName;
        this.description = description;
        this.muscleCategory = muscleCategory;
        this.createdBy = "admin";
        this.updatedBy = "admin";
    }

    public static MuscleGroup createMuscleGroup(String muscleGroupName, String description, MuscleCategory muscleCategory) {
        return builder()
                .muscleGroupName(muscleGroupName)
                .description(description)
                .muscleCategory(muscleCategory)
                .build();
    }
}
