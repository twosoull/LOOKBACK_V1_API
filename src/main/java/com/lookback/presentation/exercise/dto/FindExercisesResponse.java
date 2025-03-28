package com.lookback.presentation.exercise.dto;

import com.lookback.presentation.muscle.dto.MuscleCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindExercisesResponse {

    List<ExerciseTypeEnumDto> exerciseTypes = new ArrayList<>();
    List<ExerciseDto> strengthExercises     = new ArrayList<>();
    List<ExerciseDto> cardioExercises       = new ArrayList<>();
    List<ExerciseDto> stretchingExercises   = new ArrayList<>();
    List<MuscleCategoryDto> muscleCategories = new ArrayList<>();
    List<EquipmentDto> equipments = new ArrayList<>();

    public static FindExercisesResponse create(
            List<ExerciseTypeEnumDto> exerciseTypes ,
            List<ExerciseDto> strengthExercises ,
            List<ExerciseDto> cardioExercises ,
            List<ExerciseDto> stretchingExercises ,
            List<MuscleCategoryDto> muscleCategories ,
            List<EquipmentDto> equipments
    ) {
        return FindExercisesResponse.builder()
                .exerciseTypes(exerciseTypes)
                .strengthExercises(strengthExercises)
                .cardioExercises(cardioExercises)
                .stretchingExercises(stretchingExercises)
                .muscleCategories(muscleCategories)
                .equipments(equipments)
                .build();
    }

}
