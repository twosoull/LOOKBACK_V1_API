package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.exercise.entity.ExerciseVideo;
import com.lookback.domain.exercise.repository.ExerciseVideoRepository;
import com.lookback.infrastructure.repositoryORM.ExerciseVideoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseVideoRepositoryImpl implements ExerciseVideoRepository {

    private final ExerciseVideoJpaRepository exerciseVideoJpaRepository;

    @Override
    public void save(ExerciseVideo video) {
        exerciseVideoJpaRepository.save(video);
    }
}
