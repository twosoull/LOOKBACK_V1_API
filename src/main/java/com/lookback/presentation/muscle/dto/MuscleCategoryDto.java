package com.lookback.presentation.muscle.dto;

import com.lookback.domain.muscle.entity.MuscleCategory;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class MuscleCategoryDto {

    private Long muscleCategoryId;
    private String muscleName;
    private String muscleCategoryName;

    public static MuscleCategoryDto fromEntity(MuscleCategory muscleCategory) {
        return MuscleCategoryDto.builder()
                .muscleCategoryId(muscleCategory.getId())
                .muscleName(muscleCategory.getMuscleName())
                .muscleCategoryName(muscleCategory.getMuscleCategoryName())
                .build();
    }
}
