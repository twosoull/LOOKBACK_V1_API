package com.lookback.domain.exercise.command;

public class ExerciseCommand {

    public record retrieve(Long muscleGroupId,
                          Long muscleCategoryId){}

}
