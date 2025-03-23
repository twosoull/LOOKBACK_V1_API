package com.lookback.domain.user.repository;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.domain.user.entity.Training;
import com.lookback.infrastructure.queryDto.UserTrainingQueryDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository {
    List<Training> findByTrainerIdAndTrainingStatus(Long trainingId, String trainingStatus);

    Training save(Training training);

    Training findById(Long id);

    void deleteById(Long id);

    List<UserTrainingQueryDto> findTrainingsForTrainerOrderByCreateAt(Long trainerId, TrainingStatus trainingStatus);

    List<UserTrainingQueryDto> findTrainingsForTrainerOrderByUserName(Long trainerId, TrainingStatus trainingStatus);

    List<UserTrainingQueryDto> findTrainingsForTrainerOrderBySortByType(Long userId, TrainingStatus trainingStatus, String sort);

    Training findByTrainerIdAndStudentIdAndTrainingStatus(Long trainerId, Long usersId, TrainingStatus trainingStatus);
}
