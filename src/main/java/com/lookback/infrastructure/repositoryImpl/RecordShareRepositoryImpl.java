package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.record.repository.RecordShareRepository;
import com.lookback.infrastructure.repositoryORM.RecordShareJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordShareRepositoryImpl implements RecordShareRepository {

    private final RecordShareJpaRepository recordShareJpaRepository;

    @Override
    public void deleteById(Long id) {
        recordShareJpaRepository.deleteById(id);
    }
}
