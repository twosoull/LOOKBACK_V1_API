package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.manager.center.entity.CenterEventLog;
import com.lookback.domain.manager.center.repository.CenterEventLogRepository;
import com.lookback.infrastructure.repositoryORM.CenterEventLogJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CenterEventLogRepositoryImpl implements CenterEventLogRepository {

    private final CenterEventLogJpaRepository centerEventLogJpaRepository;

    @Override
    public CenterEventLog save(CenterEventLog log) {
        return  centerEventLogJpaRepository.save(log);
    }
}
