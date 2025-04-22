package com.lookback.presentation.exercise.dto;

import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.exercise.entity.ExerciseVideo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseVideoDto {

    private Long exerciseVideoId;
    private Long exerciseId;
    private String videoTitle;
    private String exerciseVideoUrl;

    public static ExerciseVideoDto fromEntity(ExerciseVideo exerciseVideo) {
        return ExerciseVideoDto
                .builder()
                .exerciseVideoId(exerciseVideo.getId())
                .exerciseId(exerciseVideo.getExercise().getId())
                .videoTitle(exerciseVideo.getVideoTitle())
                .exerciseVideoUrl(exerciseVideo.getExerciseVideoUrl())
                .build();
    }
}
