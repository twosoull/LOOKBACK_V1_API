package com.lookback.infrastructure.repositoryORM;


import com.lookback.domain.record.entity.ExerciseRecordDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordDetailJpaRepository extends JpaRepository<ExerciseRecordDetail, Long> {
}
