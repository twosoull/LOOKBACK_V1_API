package com.lookback.presentation.exercise.repositoryImpl;

import com.lookback.domain.handler.exception.RestApiException;
import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.repository.MuscleCategoryRepository;
import com.lookback.presentation.exercise.repositoryORM.MuscleCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.lookback.domain.handler.exception.errorCode.CommonErrorCode.RESOURCE_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class MuscleCategoryRepositoryImpl implements MuscleCategoryRepository {

    private final MuscleCategoryJpaRepository muscleCategoryJpaRepository;

    @Override
    public MuscleCategory save(MuscleCategory muscleCategory) {
        return muscleCategoryJpaRepository.save(muscleCategory);
    }

    @Override
    public MuscleCategory findById(Long muscleCategoryId) {
        return muscleCategoryJpaRepository.findById(muscleCategoryId).orElseThrow(
                () -> new RestApiException(RESOURCE_NOT_FOUND)
        );
    }
}
