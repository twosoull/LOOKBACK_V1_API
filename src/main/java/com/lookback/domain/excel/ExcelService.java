package com.lookback.domain.excel;

import com.lookback.domain.exercise.repository.ExerciseRepository;
import com.lookback.domain.muscle.entity.*;
import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.presentation.exercise.repositoryORM.ExerciseMuscleMappingRepository;
import com.lookback.presentation.exercise.repositoryORM.MuscleCategoryJpaRepository;
import com.lookback.presentation.exercise.repositoryORM.MuscleGroupJpaRepository;
import com.lookback.presentation.exercise.repositoryORM.MuscleJpaRepository;
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
    private final ExerciseMuscleMappingRepository exerciseMuscleMappingRepository;

    @Transactional
    public void importDataFromExcel(String filePath) throws Exception {
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(file);

            importMuscleCategories(workbook);
            importMuscleGroups(workbook);
            importMuscles(workbook);
            importExercises(workbook);
            importExerciseMuscleMappings(workbook);

        }
    }

    private void importMuscleCategories(Workbook workbook) {
        Sheet sheet = workbook.getSheet("MuscleCategory");
        Iterator<Row> rows = sheet.iterator();
        rows.next(); // Skip header row

        while (rows.hasNext()) {
            Row row = rows.next();
            MuscleCategory category = new MuscleCategory(
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue()
            );
            muscleCategoryRepository.save(category);
        }
    }

    private void importMuscleGroups(Workbook workbook) {
        Sheet sheet = workbook.getSheet("MuscleGroup");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            Long categoryId = (long) row.getCell(1).getNumericCellValue();
            MuscleCategory category = muscleCategoryRepository.findById(categoryId).orElse(null);

            MuscleGroup group = new MuscleGroup(
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    category
            );
            muscleGroupRepository.save(group);
        }
    }

    private void importMuscles(Workbook workbook) {
        Sheet sheet = workbook.getSheet("Muscle");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            Long groupId = (long) row.getCell(1).getNumericCellValue();
            MuscleGroup group = muscleGroupRepository.findById(groupId).orElse(null);

            Muscle muscle = new Muscle(
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    row.getCell(4).getStringCellValue(),
                    row.getCell(5).getStringCellValue(),
                    row.getCell(6).getStringCellValue(),
                    (long) row.getCell(7).getNumericCellValue(),
                    group
            );
            muscleRepository.save(muscle);
        }
    }

    private void importExercises(Workbook workbook) {
        Sheet sheet = workbook.getSheet("Exercise");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            Exercise exercise = new Exercise(
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    (int) row.getCell(4).getNumericCellValue(),
                    row.getCell(5).getStringCellValue()
            );
            exerciseRepository.save(exercise);
        }
    }

    private void importExerciseMuscleMappings(Workbook workbook) {
        Sheet sheet = workbook.getSheet("ExerciseMuscleMapping");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            Long exerciseId = (long) row.getCell(1).getNumericCellValue();
            Long primaryCategoryId = (long) row.getCell(2).getNumericCellValue();
            Long secondCategoryId = row.getCell(3) != null ? (long) row.getCell(3).getNumericCellValue() : null;

            Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
            MuscleCategory primaryCategory = muscleCategoryRepository.findById(primaryCategoryId).orElse(null);
            MuscleCategory secondCategory = secondCategoryId != null ? muscleCategoryRepository.findById(secondCategoryId).orElse(null) : null;

            ExerciseMuscleMapping mapping = new ExerciseMuscleMapping(
                    exercise,
                    primaryCategory,
                    secondCategory,
                    row.getCell(4).getStringCellValue(),
                    (long) row.getCell(5).getNumericCellValue(),
                    row.getCell(6).getStringCellValue()
            );
            exerciseMuscleMappingRepository.save(mapping);
        }
    }
}
