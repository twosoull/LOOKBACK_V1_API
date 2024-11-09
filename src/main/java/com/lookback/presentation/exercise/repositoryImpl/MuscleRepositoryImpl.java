package com.lookback.presentation.exercise.repositoryImpl;

import com.lookback.domain.muscle.entity.Muscle;
import com.lookback.domain.muscle.repository.MuscleRepository;
import com.lookback.presentation.exercise.repositoryORM.MuscleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MuscleRepositoryImpl implements MuscleRepository {

    private final MuscleJpaRepository muscleJpaRepository;

    @Override
    public Muscle save(Muscle muscle) {
        return muscleJpaRepository.save(muscle);
    }
}
