package com.lookback.infrastructure.repositoryCustom;

import com.lookback.presentation.muscle.dto.MuscleGroupDto;

import java.util.List;

public interface MuscleGroupRepositoryCustom {

    List<MuscleGroupDto> findMuscleCategoriesByExercise(List<Long> exerciseIds);
}
