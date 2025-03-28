package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.repository.MuscleCategoryRepository;
import com.lookback.infrastructure.repositoryORM.MuscleCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.RESOURCE_NOT_FOUND;

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
                () -> new RestApiException(RESOURCE_NOT_FOUND));
    }

    @Override
    public List<MuscleCategory> findByParentIsNotNull() {
        return muscleCategoryJpaRepository.findByParentIsNotNull();
    }
}
