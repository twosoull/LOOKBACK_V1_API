package com.lookback.domain.muscle.command;

import com.lookback.domain.muscle.entity.MuscleCategory;

public class MuscleCategoryCommand {

    public record Save(String muscleCategoryName,
                         String description) {}

    public record Saved(Long muscleCategoryId,
                        String muscleCategoryName,
                        String description) {}

    public static Saved of(MuscleCategory mc) {
        return new Saved(mc.getId(),
                         mc.getMuscleCategoryName(),
                         mc.getDescription());
    }

}
