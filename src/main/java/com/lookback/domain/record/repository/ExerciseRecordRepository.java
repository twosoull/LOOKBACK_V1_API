package com.lookback.domain.record.repository;

import com.lookback.domain.record.entity.ExerciseRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRecordRepository {
    List<ExerciseRecord> findByIdOrderByOrdAsc(Long recordId);
}
