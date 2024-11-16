package com.lookback.domain.exercise.command;

public class ExerciseVideoCommand {

    public record Save(
            String videoTitle,
            String exerciseVideoUrl
    ) {}
}
