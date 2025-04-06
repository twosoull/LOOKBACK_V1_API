package com.lookback.domain.record.repository;

import com.lookback.domain.record.entity.ExerciseRecordDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRecordDetailRepository {

    ExerciseRecordDetail save(ExerciseRecordDetail exerciseRecordDetail);

    ExerciseRecordDetail findById(Long exerciseRecordDetailId);
}
