package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.user.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingJpaRepository extends JpaRepository<Training, Long> {
    List<Training> findByTrainerIdAndTrainingStatus(@Param("trainerId")Long trainerId, @Param("trainingStatus")String trainingStatus);
}
