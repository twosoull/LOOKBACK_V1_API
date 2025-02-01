package com.lookback.domain.record.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface RecordShareRepository {

    void deleteById(Long id);
}
