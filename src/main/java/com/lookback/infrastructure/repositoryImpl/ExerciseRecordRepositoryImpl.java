package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.record.repository.ExerciseRecordRepository;
import com.lookback.infrastructure.repositoryORM.ExerciseRecordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExerciseRecordRepositoryImpl implements ExerciseRecordRepository {

    private final ExerciseRecordJpaRepository exerciseRecordJpaRepository;

    @Override
    public List<ExerciseRecord> findByIdOrderByOrdAsc(Long recordId) {
        return exerciseRecordJpaRepository.findByIdOrderByOrdAsc(recordId);
    }
}
