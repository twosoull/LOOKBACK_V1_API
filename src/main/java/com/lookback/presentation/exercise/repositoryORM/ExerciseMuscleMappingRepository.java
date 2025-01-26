package com.lookback.presentation.exercise.repositoryORM;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseMuscleMappingRepository extends JpaRepository<ExerciseMuscleMapping, Long> {
    List<ExerciseMuscleMapping> findByExerciseId(Long exerciseId);
    List<ExerciseMuscleMapping> findByPrimaryMuscleCategoryId(Long primaryMuscleCategoryId);
    List<ExerciseMuscleMapping> findBySecondMuscleCategoryId(Long secondMuscleCategoryId);
}
