package com.lookback.domain.muscle.command;

public class MuscleCategoryCommand {

    public record create(String muscleCategoryName,
                         String description) {}
}
