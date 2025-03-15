package com.lookback.infrastructure.repositoryCustom;

import com.lookback.domain.record.entity.Record;

import java.util.List;

public interface RecordRepositoryCustom {
    List<Record> findByUsersIdOrderByRecordDateDesc(Long usersId, String category);
}
