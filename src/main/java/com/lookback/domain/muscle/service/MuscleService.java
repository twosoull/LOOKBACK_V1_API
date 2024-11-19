package com.lookback.domain.muscle.service;

import com.lookback.domain.muscle.command.MuscleCommand;
import com.lookback.domain.muscle.entity.Muscle;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import com.lookback.domain.muscle.repository.MuscleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MuscleService {

    private final MuscleGroupRepository muscleGroupRepository;
    private final MuscleRepository muscleRepository;

    public MuscleCommand.Saved save(MuscleCommand.Save save) {
        //1) 근육카테고리 키를 받지 않는 이유는 근육그룹을 선택하면 자동으로 카테고리를 따라와야 하기 때문
        MuscleGroup muscleGroup = muscleGroupRepository.findById(save.muscleGroupId());

        return MuscleCommand.of(muscleRepository.save(Muscle.fromCommandSave(save, muscleGroup)));
    }
}
