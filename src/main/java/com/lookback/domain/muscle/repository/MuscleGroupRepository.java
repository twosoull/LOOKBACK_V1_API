package com.lookback.domain.muscle.repository;

import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import org.springframework.stereotype.Repository;

public interface MuscleGroupRepository {
    MuscleGroup save(MuscleGroup muscleGroup);

    MuscleGroup findById(Long id);

}
