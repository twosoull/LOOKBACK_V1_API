package com.lookback.domain.record.dto;

import com.lookback.presentation.muscle.dto.MuscleCategoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MuscleGroupDomainDto {
        private Long muscleGroupId;
        private MuscleCategoryDto muscleCategory;
        private String muscleType;
        private String categoryParentsName;
}
