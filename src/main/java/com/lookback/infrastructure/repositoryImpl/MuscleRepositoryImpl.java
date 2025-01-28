package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.muscle.entity.Muscle;
import com.lookback.domain.muscle.repository.MuscleRepository;
import com.lookback.infrastructure.repositoryORM.MuscleJpaRepository;
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
