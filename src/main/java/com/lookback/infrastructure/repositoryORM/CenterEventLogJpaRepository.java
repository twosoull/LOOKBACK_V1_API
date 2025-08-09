package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.manager.center.entity.CenterEventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterEventLogJpaRepository extends JpaRepository<CenterEventLog, Long> {
}
