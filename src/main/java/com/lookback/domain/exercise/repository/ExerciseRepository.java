package com.lookback.domain.exercise.repository;

import com.lookback.domain.exercise.entity.Exercise;

import java.util.List;
import java.util.Optional;


public interface ExerciseRepository {
    Exercise save(Exercise exercise);
    Exercise findById(Long exerciseId);

    List<Exercise> findAll();
}
