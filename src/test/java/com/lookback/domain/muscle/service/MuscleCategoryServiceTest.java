package com.lookback.domain.muscle.service;

import com.lookback.domain.muscle.command.MuscleCategoryCommand;

import com.lookback.domain.muscle.command.MuscleGroupCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class MuscleCategoryServiceTest {

    @Autowired
    private MuscleCategoryService muscleCategoryService;

    @Autowired MuscleGroupService muscleGroupService;

    @Test
    @Rollback(false)
    void save() {
        MuscleCategoryCommand.Save save = new MuscleCategoryCommand.Save("상체", "몸의 상체를 가르킴");
        muscleCategoryService.save(save);

    }
}