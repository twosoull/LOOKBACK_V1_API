package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.repository.CenterRepository;
import com.lookback.infrastructure.repositoryORM.CenterJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CenterRepositoryImpl implements CenterRepository {

    private final CenterJpaRepository centerJpaRepository;

    @Override
    public Center findById(Long id) {
        return centerJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Center save(Center center) {
        return centerJpaRepository.save(center);
    }
}
