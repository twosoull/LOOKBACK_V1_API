package com.lookback.infrastructure.repositoryCustom;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.infrastructure.queryDto.UserTrainingQueryDto;

import java.util.List;

public interface TrainingRepositoryCustom {
    List<UserTrainingQueryDto> findTrainingsForTrainerOrderBySortByType(Long trainerId, TrainingStatus trainingStatus, String sortBy);
}
