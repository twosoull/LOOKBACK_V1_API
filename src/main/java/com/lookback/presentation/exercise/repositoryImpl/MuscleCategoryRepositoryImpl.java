package com.lookback.presentation.exercise.repositoryImpl;

import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.repository.MuscleCategoryRepository;
import com.lookback.presentation.exercise.repositoryORM.MuscleCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MuscleCategoryRepositoryImpl implements MuscleCategoryRepository {

    private final MuscleCategoryJpaRepository muscleCategoryJpaRepository;

    @Override
    public void create(MuscleCategory muscleCategory) {
        muscleCategoryJpaRepository.save(muscleCategory);
    }
}
