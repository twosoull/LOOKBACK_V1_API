package com.lookback.domain.muscle.command;

public class MuscleGroupCommand {

    public record Save(Long muscleCategoryId,
                         String muscleGroupName,
                         String description) {}


}
