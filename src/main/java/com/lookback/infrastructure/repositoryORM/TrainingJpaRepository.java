package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.domain.user.entity.Training;
import com.lookback.infrastructure.queryDto.UserTrainingQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingJpaRepository extends JpaRepository<Training, Long> {
    List<Training> findByTrainerIdAndTrainingStatus(@Param("trainerId")Long trainerId, @Param("trainingStatus")String trainingStatus);

    @Query("SELECT new com.lookback.infrastructure.queryDto.UserTrainingQueryDto( u.id, u.userName, u.birthDt, MAX(rs.createdAt) )" +
            "FROM Training t " +
            "JOIN RecordShare c ON t.id = c.training.id " +
            "JOIN Users u ON u.id = t.student.id " +
            "LEFT JOIN RecordShare rs ON rs.training.id = t.id " +
            "WHERE t.trainer.id = :trainerId " +
            "AND t.trainingStatus = :trainingStatus " +
            "GROUP BY u.id, u.userName, u.birthDt " +
            "ORDER BY c.createdAt DESC")
    List<UserTrainingQueryDto> findTrainingsForTrainerOrderByCreateAt(@Param("trainerId") Long trainerId,
                                                       @Param("trainingStatus") TrainingStatus trainingStatus);

    @Query("SELECT new com.lookback.infrastructure.queryDto.UserTrainingQueryDto( u.id, u.userName, u.birthDt, MAX(rs.createdAt) )" +
            "FROM Training t " +
            "JOIN RecordShare c ON t.id = c.training.id " +
            "JOIN Users u ON u.id = t.student.id " +
            "LEFT JOIN RecordShare rs ON rs.training.id = t.id " +
            "WHERE t.trainer.id = :trainerId " +
            "AND t.trainingStatus = :trainingStatus " +
            "GROUP BY u.id, u.userName, u.birthDt " +
            "ORDER BY u.userName ASC")
    List<UserTrainingQueryDto> findTrainingsForTrainerOrderByUserName(@Param("trainerId") Long trainerId,
                                                                      @Param("trainingStatus") TrainingStatus trainingStatus);
}
