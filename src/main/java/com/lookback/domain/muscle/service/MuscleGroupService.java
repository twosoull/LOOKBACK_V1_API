package com.lookback.domain.muscle.service;

import com.lookback.domain.muscle.command.MuscleGroupCommand;
import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.repository.MuscleCategoryRepository;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MuscleGroupService {

    private final MuscleGroupRepository muscleGroupRepository;
    private final MuscleCategoryRepository muscleCategoryRepository;
    public void save(MuscleGroupCommand.Save save){

        MuscleCategory muscleCategory =  muscleCategoryRepository.findById(save.muscleCategoryId());
        MuscleGroup muscleGroup = MuscleGroup.createMuscleGroup(save.muscleGroupName(),
                                                                save.description(),
                                                                muscleCategory);
        muscleGroupRepository.save(muscleGroup);
    }
}
