package com.lookback.domain.muscle.command;

import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;

public class MuscleGroupCommand {

    public record Save(Long muscleCategoryId,
                       String muscleGroupName,
                       String description) {}

    public record Saved(Long muscleGroupId,
                        Long muscleCategoryId,
                        String muscleGroupName,
                        String description) {}

    public static MuscleGroupCommand.Saved of(MuscleGroup mg) {
        return new MuscleGroupCommand.Saved(mg.getId(),
                mg.getMuscleCategory().getId(),
                mg.getMuscleGroupName(),
                mg.getDescription());
    }



}
