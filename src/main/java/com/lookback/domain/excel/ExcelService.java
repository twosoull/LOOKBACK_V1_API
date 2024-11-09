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

    @Transactional
    public void importDataFromExcel(String filePath) throws Exception {
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(file);

            importMuscleCategories(workbook);
            importMuscleGroups(workbook);
            importMuscles(workbook);
            importExercises(workbook);
            importExerciseVideos(workbook);
        }
    }

    private void importMuscleCategories(Workbook workbook) {
        Sheet sheet = workbook.getSheet("MuscleCategory");
        if (sheet == null) throw new IllegalArgumentException("MuscleCategory 시트를 찾을 수 없습니다.");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            MuscleCategory category = MuscleCategory.create(
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue()
            );
            muscleCategoryRepository.save(category);
        }
    }

    private void importMuscleGroups(Workbook workbook) {
        Sheet sheet = workbook.getSheet("MuscleGroup");
        if (sheet == null) throw new IllegalArgumentException("MuscleGroup 시트를 찾을 수 없습니다.");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            Long categoryId = (long) row.getCell(1).getNumericCellValue();
            MuscleCategory category = muscleCategoryRepository.findById(categoryId).orElse(null);

            MuscleGroup group = MuscleGroup.createMuscleGroup(
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    category
            );
            muscleGroupRepository.save(group);
        }
    }

    private void importMuscles(Workbook workbook) {
        Sheet sheet = workbook.getSheet("Muscle");
        if (sheet == null) throw new IllegalArgumentException("Muscle 시트를 찾을 수 없습니다.");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            Long groupId = (long) row.getCell(1).getNumericCellValue();
            MuscleGroup group = muscleGroupRepository.findById(groupId).orElse(null);

            Muscle muscle = Muscle.create(
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
        if (sheet == null) throw new IllegalArgumentException("Exercise 시트를 찾을 수 없습니다.");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            Long categoryId = (long) row.getCell(1).getNumericCellValue();
            Long groupId = (long) row.getCell(2).getNumericCellValue();
            Long primeMuscleId = (long) row.getCell(3).getNumericCellValue();
            Long auxiliaryMuscleId = (long) row.getCell(4).getNumericCellValue();

            MuscleCategory category = muscleCategoryRepository.findById(categoryId).orElse(null);
            MuscleGroup group = muscleGroupRepository.findById(groupId).orElse(null);
            Muscle primeMuscle = muscleRepository.findById(primeMuscleId).orElse(null);
            Muscle auxiliaryMuscle = muscleRepository.findById(auxiliaryMuscleId).orElse(null);

            Exercise exercise = new Exercise(
                    row.getCell(5).getStringCellValue(),
                    row.getCell(6).getStringCellValue(),
                    row.getCell(7).getStringCellValue(),
                    (int) row.getCell(8).getNumericCellValue(),
                    row.getCell(9).getStringCellValue()
            );
            exerciseRepository.save(exercise);
        }
    }

    private void importExerciseVideos(Workbook workbook) {
        Sheet sheet = workbook.getSheet("ExerciseVideo");
        if (sheet == null) throw new IllegalArgumentException("ExerciseVideo 시트를 찾을 수 없습니다.");
        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row row = rows.next();
            Long exerciseId = (long) row.getCell(1).getNumericCellValue();
            Exercise exercise = exerciseRepository.findById(exerciseId);

            ExerciseVideo video = new ExerciseVideo(
                    exercise,
                    row.getCell(2).getStringCellValue()
            );
            exerciseVideoRepository.save(video);
        }
    }
}
