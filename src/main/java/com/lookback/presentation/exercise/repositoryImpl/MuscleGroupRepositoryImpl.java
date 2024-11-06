package com.lookback.presentation.exercise.repositoryImpl;

import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import com.lookback.presentation.exercise.repositoryORM.MuscleGroupJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MuscleGroupRepositoryImpl implements MuscleGroupRepository {

    private final MuscleGroupJpaRepository muscleGroupJpaRepository;

    @Override
    public void save(MuscleGroup muscleGroup) {
        muscleGroupJpaRepository.save(muscleGroup);
    }
}
