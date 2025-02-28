package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.repository.TrainingRepository;
import com.lookback.infrastructure.queryDto.UserTrainingQueryDto;
import com.lookback.infrastructure.repositoryORM.TrainingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrainingRepositoryImpl implements TrainingRepository {

    private final TrainingJpaRepository trainingJpaRepository;

    @Override
    public List<Training> findByTrainerIdAndTrainingStatus(Long trainingId, String trainingStatus) {
        return trainingJpaRepository.findByTrainerIdAndTrainingStatus(trainingId, trainingStatus);
    }

    @Override
    public Training save(Training training) {
        return trainingJpaRepository.save(training);
    }

    @Override
    public Training findById(Long trainingId) {
        return trainingJpaRepository.findById(trainingId).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        trainingJpaRepository.deleteById(id);
    }

    @Override
    public List<UserTrainingQueryDto> findTrainingsForTrainerOrderByCreateAt(Long trainerId, TrainingStatus trainingStatus) {
        return trainingJpaRepository.findTrainingsForTrainerOrderByCreateAt(trainerId, trainingStatus);
    }

    @Override
    public List<UserTrainingQueryDto> findTrainingsForTrainerOrderByUserName(Long trainerId, TrainingStatus trainingStatus) {
        return trainingJpaRepository.findTrainingsForTrainerOrderByUserName(trainerId, trainingStatus);
    }
}
