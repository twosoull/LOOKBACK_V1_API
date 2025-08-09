package com.lookback.domain.manager.center.repository;

import com.lookback.domain.manager.center.entity.CenterEventLog;

public interface CenterEventLogRepository {
    CenterEventLog save(CenterEventLog log);
}
