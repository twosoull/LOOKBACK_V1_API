package com.lookback.domain.exercise.command;

import java.time.LocalDateTime;
import java.util.List;

public class ExerciseCommand {

    public record retrieve(Long muscleGroupId,
                          Long muscleCategoryId){}

    public record Save( Long MuscleGroupId,
                        Long primeMuscleId,
                        Long auxiliaryMuscleId,
                        String exerciseName,
                        String exerciseLevel,
                        String equipment,
                        Integer caloriesBurned,
                        String description,
                        List<ExerciseVideoCommand.Save> videos
    ) {}

}
