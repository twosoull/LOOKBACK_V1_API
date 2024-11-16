package com.lookback.domain.exercise.service;

import com.lookback.domain.exercise.command.ExerciseCommand;
import com.lookback.domain.exercise.command.ExerciseVideoCommand;
import com.lookback.domain.muscle.command.MuscleCategoryCommand;
import com.lookback.domain.muscle.command.MuscleCommand;
import com.lookback.domain.muscle.command.MuscleGroupCommand;
import com.lookback.domain.muscle.service.MuscleCategoryService;
import com.lookback.domain.muscle.service.MuscleGroupService;
import com.lookback.domain.muscle.service.MuscleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExerciseServiceTest {

    @Autowired
    MuscleCategoryService muscleCategoryService;
    @Autowired
    MuscleGroupService muscleGroupService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    private MuscleService muscleService;


    @Test
    @Rollback(false)
    void save() {
        MuscleCategoryCommand.Save save = new MuscleCategoryCommand.Save("상체", "몸의 상체를 가르킴");
        MuscleCategoryCommand.Saved saved = muscleCategoryService.save(save);

        MuscleGroupCommand.Saved savedGroupCommand = muscleGroupService.save(new MuscleGroupCommand.Save(saved.id(),
                "등", "등운동이다."
        ));

        MuscleCommand.Saved savedMuscle = muscleService.save(new MuscleCommand.Save(savedGroupCommand.muscleGroupId(),
                "이두",
                "이두", //근육의 기시부(근육이 시작되는 부위)
                "이두끝", //근육의 정지부(근육이 끝나는 부위)
                "팔", //역할
                "물건을 들어올릴 때?",
                5L
        ));

        ExerciseVideoCommand.Save save1 = new ExerciseVideoCommand.Save("title", "url");
        ExerciseVideoCommand.Save save2 = new ExerciseVideoCommand.Save("title2", "url2");

        List<ExerciseVideoCommand.Save> saveList = new ArrayList<>();
        saveList.add(save1);
        saveList.add(save2);

        ExerciseCommand.Save saveExercise = new ExerciseCommand.Save(savedGroupCommand.muscleGroupId(),
                savedMuscle.muscleId(),
                null,
                "근육1",
                "0",
                "강",
                0,
                "설명",
                saveList
        );

        exerciseService.save(saveExercise);
    }
}