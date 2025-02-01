package com.lookback.domain.user.repository;

import com.lookback.domain.user.entity.Training;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository {
    List<Training> findByTrainerIdAndTrainingStatus(Long trainingId, String trainingStatus);

    Training save(Training training);

    Training findById(Long id);

    void deleteById(Long id);
}
