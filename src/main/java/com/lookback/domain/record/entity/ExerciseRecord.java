package com.lookback.domain.record.entity;

import com.lookback.domain.exercise.entity.Exercise;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EXERCISE_RECORD")
public class ExerciseRecord {

    @Id @GeneratedValue
    @Column(name = "EXERCISE_RECORD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_ID")
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID")
    private Exercise exercise;

    private Integer sets;
    private Integer repsPerSet;
    private Integer weight;
    private Integer duration;
    private String memo;
    private Integer ord;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
