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

    //private MuscleCategoryDto muscleCategory;
    private String muscleType;
    private String categoryParentsName;

    public static MuscleGroupDto fromMuscleGroup(String muscleName) {
        return MuscleGroupDto.builder()
                .categoryParentsName(muscleName)
                .build();
    }
}
