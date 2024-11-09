package com.lookback.domain.muscle.service;

import com.lookback.domain.muscle.command.MuscleCategoryCommand;
import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.repository.MuscleCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MuscleCategoryService {

    private final MuscleCategoryRepository muscleCategoryRepository;

    public MuscleCategoryCommand.Saved save(MuscleCategoryCommand.Save save) {
        return MuscleCategoryCommand.of(muscleCategoryRepository.save(MuscleCategory.fromCommandSave(save)));
    }

}
