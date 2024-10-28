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

    // 기본 생성자
    protected Muscle() {}

    // Excel 데이터를 위한 생성자
    public Muscle(String muscleName, String origin, String insertion, String role,
                  String description, Long activationLevel, MuscleGroup muscleGroup) {
        this.muscleName = muscleName;
        this.origin = origin;
        this.insertion = insertion;
        this.role = role;
        this.description = description;
        this.activationLevel = activationLevel;
        this.muscleGroup = muscleGroup;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "admin";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "admin";
    }
}
