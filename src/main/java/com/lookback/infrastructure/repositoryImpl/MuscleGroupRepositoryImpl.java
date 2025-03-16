package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import com.lookback.infrastructure.repositoryORM.MuscleGroupJpaRepository;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.RESOURCE_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class MuscleGroupRepositoryImpl implements MuscleGroupRepository {

    private final MuscleGroupJpaRepository muscleGroupJpaRepository;

    @Override
    public MuscleGroup save(MuscleGroup muscleGroup) {
        return muscleGroupJpaRepository.save(muscleGroup);
    }

    @Override
    public MuscleGroup findById(Long id) {
        return muscleGroupJpaRepository.findById(id).orElseThrow(
                () -> new RestApiException(RESOURCE_NOT_FOUND));
    }

    @Override
    public List<MuscleGroupDto> findMuscleCategoriesByExercise(List<Long> exerciseIds) {
        return muscleGroupJpaRepository.findMuscleCategoriesByExercise(exerciseIds);
    }

}
