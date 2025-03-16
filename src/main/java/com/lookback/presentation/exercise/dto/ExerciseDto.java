package com.lookback.presentation.exercise.dto;

import com.lookback.domain.common.constant.enums.ExerciseTypeEnum;
import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ExerciseDto {
    private Long exerciseId;
    private String exerciseType;
    private MuscleGroupDto muscleGroup;

    public static ExerciseDto fromEntity(Exercise exercise) {
        return ExerciseDto.builder()
                .exerciseId(exercise.getId())
                .exerciseType(exercise.getExerciseType().name())
                .build();
    }
}
