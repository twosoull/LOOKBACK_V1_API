package com.lookback.domain.exercise.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ExerciseVideo {

    @Id @GeneratedValue
    @Column(name = "EXERCISE_VIDEO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID")
    private Exercise exercise;

    private String exerciseVideoUrl;
}
