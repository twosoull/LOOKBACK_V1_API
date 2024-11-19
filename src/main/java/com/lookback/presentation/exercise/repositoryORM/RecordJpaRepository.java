package com.lookback.presentation.exercise.repositoryORM;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lookback.domain.record.entity.Record;

public interface RecordJpaRepository extends JpaRepository<Record, Long> {
}
