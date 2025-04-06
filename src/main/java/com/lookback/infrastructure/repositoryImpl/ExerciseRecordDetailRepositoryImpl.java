package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.record.entity.ExerciseRecordDetail;
import com.lookback.domain.record.repository.ExerciseRecordDetailRepository;
import com.lookback.infrastructure.repositoryORM.ExerciseRecordDetailJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseRecordDetailRepositoryImpl implements ExerciseRecordDetailRepository {

    private final ExerciseRecordDetailJpaRepository exerciseRecordDetailJpaRepository;

    @Override
    public ExerciseRecordDetail save(ExerciseRecordDetail exerciseRecordDetail) {
        return exerciseRecordDetailJpaRepository.save(exerciseRecordDetail);
    }

    @Override
    public ExerciseRecordDetail findById(Long exerciseRecordDetailId) {
        return exerciseRecordDetailJpaRepository.findById(exerciseRecordDetailId).orElse(null);
    }
}
