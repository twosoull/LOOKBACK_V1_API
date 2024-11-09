package com.lookback.domain.exercise.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ExerciseVideo extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "EXERCISE_VIDEO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Exercise exercise;

    private String exerciseVideoUrl;

    public ExerciseVideo(Exercise exercise, String exerciseVideoUrl) {
        this.exercise = exercise;
        this.exerciseVideoUrl = exerciseVideoUrl;
    }

    public ExerciseVideo() {

    }
}
