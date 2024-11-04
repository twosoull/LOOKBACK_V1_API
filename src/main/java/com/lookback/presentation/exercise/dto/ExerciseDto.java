package com.lookback.presentation.exercise.dto;

public class ExerciseDto {

    public record Request(Long muscleGroupId,
                          Long muscleCategoryId){}

    public record Response(Long muscleCategoryId,
                           String muscleCategoryName,
                           Long muscleGroupId,
                           String muscleGroupName,
                           Long exerciseId,
                           String exerciseName,
                           String exerciseLevel,
                           String equipment, //장비
                           Long caloriesBurned, //소요 칼로리
                           String description
                           ){}
}
