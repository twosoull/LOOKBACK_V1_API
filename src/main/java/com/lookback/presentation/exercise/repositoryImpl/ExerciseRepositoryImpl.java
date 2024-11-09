package com.lookback.presentation.exercise.repositoryImpl;

import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.exercise.repository.ExerciseRepository;
import com.lookback.domain.handler.exception.RestApiException;
import com.lookback.presentation.exercise.repositoryORM.ExerciseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.lookback.domain.handler.exception.errorCode.CommonErrorCode.RESOURCE_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class ExerciseRepositoryImpl implements ExerciseRepository {

    private final ExerciseJpaRepository exerciseJpaRepository;

    public Exercise save(Exercise exercise) {
        return exerciseJpaRepository.save(exercise);
    }

    @Override
    public Exercise findById(Long exerciseId) {
        return exerciseJpaRepository.findById(exerciseId).orElseThrow(
                () -> new RestApiException(RESOURCE_NOT_FOUND));
    }
}
