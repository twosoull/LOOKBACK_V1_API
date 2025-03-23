package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.domain.user.entity.Training;
import com.lookback.infrastructure.queryDto.UserTrainingQueryDto;
import com.lookback.infrastructure.repositoryCustom.TrainingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingJpaRepository extends JpaRepository<Training, Long>, TrainingRepositoryCustom {
    List<Training> findByTrainerIdAndTrainingStatus(@Param("trainerId")Long trainerId, @Param("trainingStatus")String trainingStatus);

    Training findByTrainerIdAndStudentIdAndTrainingStatus(Long trainerId, Long usersId, TrainingStatus trainingStatus);
}
