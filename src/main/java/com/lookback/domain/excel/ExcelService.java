package com.lookback.domain.excel;

import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.exercise.entity.ExerciseVideo;
import com.lookback.domain.exercise.repository.ExerciseRepository;
import com.lookback.domain.exercise.repository.ExerciseVideoRepository;
import com.lookback.domain.muscle.entity.*;
import com.lookback.presentation.exercise.repositoryORM.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final MuscleCategoryJpaRepository muscleCategoryRepository;
    private final MuscleGroupJpaRepository muscleGroupRepository;
    private final MuscleJpaRepository muscleRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseVideoRepository exerciseVideoRepository;

}
