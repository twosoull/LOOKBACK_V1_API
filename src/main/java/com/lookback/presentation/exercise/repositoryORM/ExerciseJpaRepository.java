package com.lookback.presentation.exercise.repositoryORM;

import com.lookback.domain.exercise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseJpaRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByExerciseName(String exerciseName);
}
