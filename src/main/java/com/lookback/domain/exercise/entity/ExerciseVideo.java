package com.lookback.domain.exercise.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.exercise.command.ExerciseCommand;
import com.lookback.domain.exercise.command.ExerciseVideoCommand;
import com.lookback.domain.muscle.entity.MuscleGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Entity
public class ExerciseVideo extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "EXERCISE_VIDEO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Exercise exercise;

    private String videoTitle;
    private String exerciseVideoUrl;

    public ExerciseVideo(Exercise exercise, String exerciseVideoUrl) {
        this.exercise = exercise;
        this.exerciseVideoUrl = exerciseVideoUrl;
    }

    public ExerciseVideo() {

    }

    public static ExerciseVideo create(String videoTitle, String exerciseVideoUrl, Exercise exercise) {
        return builder().videoTitle(videoTitle)
                        .exercise(exercise)
                        .exerciseVideoUrl(exerciseVideoUrl).build();
    }

    public static ExerciseVideo fromCommandSave(ExerciseVideoCommand.Save save, Exercise exercise) {
        return create(save.videoTitle(),
                      save.exerciseVideoUrl(),
                      exercise);
    }
}

