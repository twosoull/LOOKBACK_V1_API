package com.lookback.domain.exercise.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.ExerciseTypeEnum;
import com.lookback.domain.exercise.command.ExerciseCommand;
import com.lookback.domain.muscle.entity.Equipment;
import com.lookback.domain.muscle.entity.MuscleGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "EXERCISE")
public class Exercise extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "EXERCISE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EQUIPMENT_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Equipment equipment;

    @Builder.Default
    @OneToMany(mappedBy = "exercise")
    private List<ExerciseVideo> exerciseVideos = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "exercise")
    private List<MuscleGroup> muscleGroups;

    private String exerciseName;
    private String exerciseLevel;
    private Integer caloriesBurned;
    private String description;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ExerciseTypeEnum exerciseType;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // 기본 생성자
    public Exercise() {
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
