package com.lookback.presentation.exercise.dto;

import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ExerciseDto {
    private Long exerciseId;
    private EquipmentDto equipmentCategory;
    private List<MuscleGroupDto> muscleGroup;
    private String exerciseType;
    private String exerciseName;
    private String exerciseLevel;
    private Integer caloriesBurned;
    private String description;
    private String imageUrl;
    //private List<MuscleGroup> muscleGroups = new ArrayList<>();
    //private List<ExerciseVideo> exerciseVideos = new ArrayList<>();

    public static ExerciseDto fromEntity(Exercise exercise) {
        return ExerciseDto.builder()
                .exerciseId(exercise.getId())
                .equipmentCategory(EquipmentDto.fromEntity(exercise.getEquipment()))
                .muscleGroup(exercise.getMuscleGroups()
                        .stream()
                        .map(mg -> MuscleGroupDto.fromEntity(mg))
                        .toList())
                .exerciseType(exercise.getExerciseType().name())
                .exerciseName(exercise.getExerciseName())
                .exerciseLevel(exercise.getExerciseLevel())
                .caloriesBurned(exercise.getCaloriesBurned())
                .description(exercise.getDescription())
                .imageUrl(exercise.getImageUrl())
                .build();
    }
}
