package com.lookback.domain.record.repository;

import com.lookback.domain.record.entity.Record;

public interface RecordRepository {
    Record save(Record record);
}
