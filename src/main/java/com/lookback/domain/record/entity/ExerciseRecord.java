package com.lookback.domain.record.entity;

import com.lookback.domain.exercise.entity.Exercise;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "EXERCISE_RECORD")
public class ExerciseRecord {

    @Id @GeneratedValue
    @Column(name = "EXERCISE_RECORD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Exercise exercise;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "exerciseRecord", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ExerciseRecordFile> exerciseRecordFiles;

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
