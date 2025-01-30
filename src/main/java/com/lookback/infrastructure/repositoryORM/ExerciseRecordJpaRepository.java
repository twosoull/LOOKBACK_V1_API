package com.lookback.infrastructure.repositoryORM;


import com.lookback.domain.record.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRecordJpaRepository extends JpaRepository<ExerciseRecord, Long> {
    List<ExerciseRecord> findByIdOrderByOrdAsc(Long recordId);
}
