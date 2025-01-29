package com.lookback.domain.exercise.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.exercise.command.ExerciseCommand;
import com.lookback.domain.muscle.entity.EquipmentCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "EXERCISE")
public class Exercise extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "EXERCISE_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EQUIPMENT_CATEGORY_ID")
    private EquipmentCategory equipmentCategory;

    @Builder.Default
    @OneToMany(mappedBy = "exercise")
    private List<ExerciseVideo> exerciseVideos = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "exercise")
    private List<MuscleGroup> muscleGroups = new ArrayList<>();

    private String exerciseName;
    private String exerciseLevel;
    private Integer caloriesBurned;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // 기본 생성자
    protected Exercise() {
    }

    // Excel 데이터를 위한 생성자
    public Exercise(String exerciseName, String exerciseLevel,
                    Integer caloriesBurned, String description) {
        this.exerciseName = exerciseName;
        this.exerciseLevel = exerciseLevel;
        this.caloriesBurned = caloriesBurned;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "admin";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "admin";
    }

    public static Exercise create(String exerciseName, String exerciseLevel,
                                  Integer caloriesBurned, String description) {

        return builder().exerciseName(exerciseName)
                        .exerciseLevel(exerciseLevel)
                        .caloriesBurned(caloriesBurned)
                        .description(description).build();
    }

    public static Exercise fromCommandSave(ExerciseCommand.Save save, MuscleGroup muscleGroup) {
        return create(save.exerciseName(),
                      save.exerciseLevel(),
                      save.caloriesBurned(),
                      save.description());
    }
}
