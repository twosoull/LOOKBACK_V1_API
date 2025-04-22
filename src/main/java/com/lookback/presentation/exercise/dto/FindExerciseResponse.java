package com.lookback.presentation.exercise.dto;

import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.exercise.entity.ExerciseVideo;
import com.lookback.domain.muscle.entity.Equipment;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindExerciseResponse {
    //TODO 나중에 내용에 맞게 안으로 넣어줌
    private ExerciseDto exercise;
    private EquipmentDto equipment;
    private List<MuscleGroupDto> agonistList = new ArrayList<>();
    private List<MuscleGroupDto> synergistList = new ArrayList<>();
    private List<ExerciseVideoDto> exerciseVideos = new ArrayList<>();

    public static FindExerciseResponse create(Exercise exercise,
                                              List<MuscleGroup> agonistList,
                                              List<MuscleGroup> synergistList,
                                              List<ExerciseVideo> exerciseVideos,
                                              Equipment equipment) {
        return FindExerciseResponse.builder()
                .exercise(ExerciseDto.fromEntity(exercise))
                .equipment(EquipmentDto.fromEntity(equipment))
                .agonistList(agonistList.stream().map(MuscleGroupDto::fromEntity).toList())
                .synergistList(synergistList.stream().map(MuscleGroupDto::fromEntity).toList())
                .exerciseVideos(exerciseVideos.stream().map(ExerciseVideoDto::fromEntity).toList())
                .build();
    }

}
