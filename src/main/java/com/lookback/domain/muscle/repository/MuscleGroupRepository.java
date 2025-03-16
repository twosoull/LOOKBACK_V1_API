package com.lookback.domain.muscle.repository;

import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MuscleGroupRepository {
    MuscleGroup save(MuscleGroup muscleGroup);

    MuscleGroup findById(Long id);

    List<MuscleGroupDto> findMuscleCategoriesByExercise(List<Long> exerciseIds);
}
