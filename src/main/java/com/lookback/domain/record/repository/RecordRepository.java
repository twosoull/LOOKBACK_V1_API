package com.lookback.domain.record.repository;

import com.lookback.domain.record.entity.Record;
import com.lookback.presentation.record.dto.FindRecordRequest;

import java.util.List;

public interface RecordRepository {
    Record save(Record record);

    List<Record> findByUsersIdOrderByCreatedAtDesc(Long UsersId);

    List<Record> findByUsersIdAndTrainingIdIsNotNullOrderByCreatedAtDesc(Long usersId);

    List<Record> findByUsersIdAndTrainingIdIsNullOrderByCreatedAtDesc(Long usersId);

    Record findById(Long recordId);

    void deleteById(Long id);
}
