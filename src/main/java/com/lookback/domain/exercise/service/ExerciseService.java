package com.lookback.domain.exercise.service;

import com.lookback.domain.exercise.command.ExerciseCommand;
import com.lookback.domain.exercise.command.ExerciseVideoCommand;
import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.exercise.entity.ExerciseVideo;
import com.lookback.domain.exercise.repository.ExerciseRepository;
import com.lookback.domain.exercise.repository.ExerciseVideoRepository;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final ExerciseVideoRepository exerciseVideoRepository;
    public void save(ExerciseCommand.Save save) {

        MuscleGroup findMuscleGroup = muscleGroupRepository.findById(save.MuscleGroupId());

        Exercise exercise = Exercise.fromCommandSave(save, findMuscleGroup);
        exerciseRepository.save(exercise);

        Optional.ofNullable(save.videos())
                .ifPresent(videos -> {
                    videos.stream()
                            .map(video -> ExerciseVideo.fromCommandSave(video, exercise))
                            .forEach(exerciseVideo -> {

                                log.info("통과하나");
                                        exercise.getExerciseVideos().add(exerciseVideo);
                                        exerciseVideoRepository.save(exerciseVideo);
                                    }
                            );
                });
    }
}
