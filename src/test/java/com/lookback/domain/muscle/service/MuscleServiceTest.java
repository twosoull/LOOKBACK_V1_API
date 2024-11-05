package com.lookback.domain.muscle.service;

import com.lookback.domain.muscle.command.MuscleCategoryCommand;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class MuscleServiceTest {

    @Autowired
    private MuscleService muscleService;

    @Test
    @Rollback(false)
    void createMuscle() {
        MuscleCategoryCommand.create create = new MuscleCategoryCommand.create("상체", "몸의 상체를 가르킴");
        muscleService.create(create);
    }
}