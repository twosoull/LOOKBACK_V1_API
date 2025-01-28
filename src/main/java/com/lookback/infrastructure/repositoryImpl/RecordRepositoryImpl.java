package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.record.entity.Record;
import com.lookback.domain.record.repository.RecordRepository;
import com.lookback.infrastructure.repositoryORM.RecordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepository {

    private final RecordJpaRepository recordJpaRepository;

    @Override
    public Record save(Record record) {
        return recordJpaRepository.save(record);
    }
}
