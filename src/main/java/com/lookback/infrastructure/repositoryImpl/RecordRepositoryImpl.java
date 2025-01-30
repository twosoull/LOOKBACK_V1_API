package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.record.entity.Record;
import com.lookback.domain.record.repository.RecordRepository;
import com.lookback.infrastructure.repositoryORM.RecordJpaRepository;
import com.lookback.presentation.record.dto.FindRecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepository {

    private final RecordJpaRepository recordJpaRepository;

    @Override
    public Record save(Record record) {
        return recordJpaRepository.save(record);
    }

    @Override
    public List<Record> findByUsersIdOrderByCreatedAtDesc(Long usersId) {
        return recordJpaRepository.findByUsersIdOrderByCreatedAtDesc(usersId);
    }

    @Override
    public List<Record> findByUsersIdAndTrainingIdIsNotNullOrderByCreatedAtDesc(Long usersId) {
        return recordJpaRepository.findByUsersIdAndTrainingIdIsNotNullOrderByCreatedAtDesc(usersId);
    }

    @Override
    public List<Record> findByUsersIdAndTrainingIdIsNullOrderByCreatedAtDesc(Long usersId) {
        return recordJpaRepository.findByUsersIdAndTrainingIdIsNullOrderByCreatedAtDesc(usersId);
    }
}
