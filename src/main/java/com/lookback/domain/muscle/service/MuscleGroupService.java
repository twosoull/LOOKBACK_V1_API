package com.lookback.domain.muscle.service;

import com.lookback.domain.muscle.command.MuscleGroupCommand;
import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.repository.MuscleCategoryRepository;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MuscleGroupService {

    private final MuscleGroupRepository muscleGroupRepository;
    private final MuscleCategoryRepository muscleCategoryRepository;


    public MuscleGroupCommand.Saved save(MuscleGroupCommand.Save save){
        MuscleCategory muscleCategory =  muscleCategoryRepository.findById(save.muscleCategoryId());
        return MuscleGroupCommand.of(muscleGroupRepository.save(MuscleGroup.fromCommandSave(save, muscleCategory)));
    }
}
