package com.lookback.domain.excel;

import com.lookback.domain.exercise.repository.ExerciseRepository;
import com.lookback.domain.exercise.repository.ExerciseVideoRepository;
import com.lookback.infrastructure.repositoryORM.MuscleCategoryJpaRepository;
import com.lookback.infrastructure.repositoryORM.MuscleGroupJpaRepository;
import com.lookback.infrastructure.repositoryORM.MuscleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final MuscleCategoryJpaRepository muscleCategoryRepository;
    private final MuscleGroupJpaRepository muscleGroupRepository;
    private final MuscleJpaRepository muscleRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseVideoRepository exerciseVideoRepository;

}
