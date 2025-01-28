package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.repository.TrainingRepository;
import com.lookback.infrastructure.repositoryORM.TrainingJpaRepository;
import lombok.RequiredArgsConstructor;
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
}
