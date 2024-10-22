package com.lookback.domain.muscle.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Muscle {

    @Id @GeneratedValue
    @Column(name = "MUSCLE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUSCLE_GROUP_ID")
    private MuscleGroup muscleGroup;

    private String muscleName;
    private String origin; //근육의 기시부(근육이 시작되는 부위)
    private String insertion; //근육의 정지부(근육이 끝나는 부위)
    private String role; //역할
    private String description;
    private Long activationLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

}
