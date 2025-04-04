package com.lookback.domain.muscle.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.MuscleTypeEnum;
import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.muscle.command.MuscleGroupCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
public class MuscleGroup extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MUSCLE_GROUP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUSCLE_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MuscleCategory muscleCategory;

    @Enumerated(EnumType.STRING)
    private MuscleTypeEnum muscleType;
    private String createdBy;
    private String updatedBy;

    // 기본 생성자
    protected MuscleGroup() {}

    // Excel 데이터를 위한 생성자
    public MuscleGroup(String muscleGroupName, String description, MuscleCategory muscleCategory) {
        this.muscleCategory = muscleCategory;
        this.createdBy = "admin";
        this.updatedBy = "admin";
    }

    public static MuscleGroup createMuscleGroup(String muscleGroupName, String description, MuscleCategory muscleCategory) {
        return builder()
                .muscleCategory(muscleCategory)
                .build();
    }

    public static MuscleGroup fromCommandSave(MuscleGroupCommand.Save command, MuscleCategory muscleCategory) {
        return createMuscleGroup(command.muscleGroupName(),
                                 command.description(),
                                 muscleCategory);
    }
}
