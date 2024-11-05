package com.lookback.domain.muscle.service;

import com.lookback.domain.muscle.command.MuscleCategoryCommand;
import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.repository.MuscleCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MuscleService {

    private final MuscleCategoryRepository muscleCategoryRepository;

    @Transactional
    public void create(MuscleCategoryCommand.create create) {

        MuscleCategory muscleCategory =  MuscleCategory.createCategory(create.muscleCategoryName(), create.description());
        muscleCategoryRepository.create(muscleCategory);

    }
}
