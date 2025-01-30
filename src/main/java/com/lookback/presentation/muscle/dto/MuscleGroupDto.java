package com.lookback.presentation.muscle.dto;

import com.lookback.domain.muscle.entity.MuscleCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MuscleGroupDto {

    private MuscleCategoryDto muscleCategory;
    private String muscleType;

}
