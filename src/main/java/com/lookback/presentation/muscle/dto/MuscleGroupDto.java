package com.lookback.presentation.muscle.dto;

import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class MuscleGroupDto {

    private Long muscleGroupId;
    private MuscleCategoryDto muscleCategory;
    private String muscleType;
    private String categoryParentsName;
    private String muscleName;

    public static MuscleGroupDto fromEntity(MuscleGroup muscleGroup) {
        return MuscleGroupDto.builder()
                .muscleGroupId(muscleGroup.getId())
                .muscleCategory(MuscleCategoryDto.fromEntity(muscleGroup.getMuscleCategory()))
                .muscleType(muscleGroup.getMuscleType().name())
                .muscleName(muscleGroup.getMuscleCategory().getMuscleName())
                .build();
    }

    public static MuscleGroupDto fromMuscleGroup(String muscleName) {
        return MuscleGroupDto.builder()
                .categoryParentsName(muscleName)
                .build();
    }
}
