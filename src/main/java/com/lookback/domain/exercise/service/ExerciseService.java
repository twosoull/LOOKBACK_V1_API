package com.lookback.domain.exercise.service;

import com.lookback.domain.common.constant.enums.ExerciseTypeEnum;
import com.lookback.domain.exercise.command.ExerciseCommand;
import com.lookback.domain.exercise.command.ExerciseVideoCommand;
import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.exercise.entity.ExerciseVideo;
import com.lookback.domain.exercise.repository.ExerciseRepository;
import com.lookback.domain.exercise.repository.ExerciseVideoRepository;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import com.lookback.presentation.exercise.dto.ExerciseTypeEnumDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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

    public void findExercises() {

        List<ExerciseTypeEnumDto> list = Arrays.stream(ExerciseTypeEnum.values()).map(
                (exerciseTypeEnum) -> ExerciseTypeEnumDto.of(exerciseTypeEnum.name(), exerciseTypeEnum.getMessage())
        ).toList();

        //모든 운동 불러옴

        //카테고리로 각각 리스트 넣기
        //enumCategory, strengths, cardios, strechings 총 4개로 만든다.
        // 여기서 운동들에는 각각 근육id가 맞게 들어가줘야함.
        // 서브 카테고리들도 장비, 근육 카테고리 불러오기 좀 많이 불러와야겠네


    }
}
