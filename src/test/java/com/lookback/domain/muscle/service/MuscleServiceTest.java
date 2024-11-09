package com.lookback.domain.muscle.service;

import com.lookback.domain.muscle.command.MuscleCategoryCommand;
import com.lookback.domain.muscle.command.MuscleCommand;
import com.lookback.domain.muscle.command.MuscleGroupCommand;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MuscleServiceTest {

    @Autowired
    private MuscleService muscleService;

    @Autowired
    private MuscleCategoryService muscleCategoryService;

    @Autowired
    private MuscleGroupService muscleGroupService;

    @Test
    @Rollback(false)
    void save() {
        MuscleCategoryCommand.Save save = new MuscleCategoryCommand.Save("상체", "몸의 상체를 가르킴");
        MuscleCategoryCommand.Saved savedCategory = muscleCategoryService.save(save);

        MuscleGroupCommand.Saved savedGroup = muscleGroupService.save(new MuscleGroupCommand.Save(savedCategory.id(),
                "등", "등운동이다."
        ));

        muscleService.save(new MuscleCommand.Save(savedGroup.muscleGroupId(),
                "이두",
                "이두", //근육의 기시부(근육이 시작되는 부위)
                "이두끝", //근육의 정지부(근육이 끝나는 부위)
                "팔", //역할
                "물건을 들어올릴 때?",
                5L
        ));
    }
}