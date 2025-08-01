package com.lookback.domain.exercise.service;

import com.lookback.domain.common.constant.enums.ExerciseTypeEnum;
import com.lookback.domain.common.constant.enums.MuscleTypeEnum;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode;
import com.lookback.domain.exercise.command.ExerciseCommand;
import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.exercise.entity.ExerciseVideo;
import com.lookback.domain.exercise.repository.EquipmentRepository;
import com.lookback.domain.exercise.repository.ExerciseRepository;
import com.lookback.domain.exercise.repository.ExerciseVideoRepository;
import com.lookback.domain.muscle.entity.Equipment;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.repository.MuscleCategoryRepository;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import com.lookback.presentation.exercise.dto.*;
import com.lookback.presentation.muscle.dto.MuscleCategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseVideoRepository exerciseVideoRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final MuscleCategoryRepository muscleCategoryRepository;
    private final EquipmentRepository equipmentRepository;

    public void save(ExerciseCommand.Save save) {

        MuscleGroup findMuscleGroup = muscleGroupRepository.findById(save.MuscleGroupId());

        Exercise exercise = Exercise.fromCommandSave(save, findMuscleGroup);
        exerciseRepository.save(exercise);

        Optional.ofNullable(save.videos())
                .ifPresent(videos -> {
                    videos.stream()
                            .map(video -> ExerciseVideo.fromCommandSave(video, exercise))
                            .forEach(exerciseVideo -> {
                                exercise.getExerciseVideos().add(exerciseVideo);
                                exerciseVideoRepository.save(exerciseVideo);
                            });
                });
    }

    @Transactional(readOnly = true)
    public FindExercisesResponse findExercises() {
        try {
            List<Exercise> exercises = exerciseRepository.findAll();
            List<ExerciseTypeEnumDto> exerciseTypes = Arrays.stream(ExerciseTypeEnum.values()).map(
                    (exerciseTypeEnum) -> ExerciseTypeEnumDto.of(exerciseTypeEnum.name(), exerciseTypeEnum.getMessage())
            ).toList();


            List<ExerciseDto> strengthExercises = new ArrayList<>();
            List<ExerciseDto> cardioExercises = new ArrayList<>();
            List<ExerciseDto> stretchingExercises = new ArrayList<>();

            //카테고리로 각각 리스트 넣기
            exercises.stream().forEach(e -> {
                if (e.getExerciseType().equals(ExerciseTypeEnum.STRENGTH)) {
                    strengthExercises.add(ExerciseDto.fromEntity(e));
                }
                if(e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)){
                    cardioExercises.add(ExerciseDto.fromEntity(e));
                }
                if(e.getExerciseType().equals(ExerciseTypeEnum.STRETCHING)){
                    stretchingExercises.add(ExerciseDto.fromEntity(e));
                }
            });

            List<MuscleCategoryDto> muscleCategories =
                    muscleCategoryRepository
                            .findByParentIsNotNull()
                            .stream()
                            .map(mc -> MuscleCategoryDto.fromEntity(mc))
                            .toList();

            List<EquipmentDto> equipments =
                    equipmentRepository.findAll()
                            .stream()
                            .map(e -> EquipmentDto.fromEntity(e))
                            .toList();
            return FindExercisesResponse.create(
                    exerciseTypes,
                    strengthExercises,
                    cardioExercises,
                    stretchingExercises,
                    muscleCategories,
                    equipments
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    //TODO 후에 한방 쿼리로 변경
    public FindExerciseResponse findExercise(Long exerciseId) {
        if (exerciseId == null) throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        Exercise findExercise = exerciseRepository.findById(exerciseId);

        List<MuscleGroup> agonistList      = findExercise.getAgonistMuscleGroupList();   //주동근
        List<MuscleGroup> synergistList    = findExercise.getSynergistMuscleGroupList(); //보조근
        List<ExerciseVideo> exerciseVideos = findExercise.getExerciseVideos();
        Equipment equipment = findExercise.getEquipment();

        FindExerciseResponse findExerciseResponse = FindExerciseResponse.create(findExercise, agonistList, synergistList, exerciseVideos, equipment);

        return findExerciseResponse;
    }
}
