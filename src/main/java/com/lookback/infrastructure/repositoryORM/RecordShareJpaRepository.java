package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.record.entity.RecordShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordShareJpaRepository extends JpaRepository<RecordShare, Long> {
}
