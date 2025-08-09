package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.manager.center.entity.CenterTrainer;
import com.lookback.domain.manager.center.repository.CenterTrainerRepository;
import com.lookback.infrastructure.repositoryORM.CenterTrainerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CenterTrainerRepositoryImpl implements CenterTrainerRepository {

    private final CenterTrainerJpaRepository centerTrainerJpaRepository;

    @Override
    public Page<CenterTrainer> findByCenterId(Long centerId, Pageable pageable) {
        return centerTrainerJpaRepository.findByCenterId(centerId, pageable);
    }

    @Override
    public CenterTrainer findByIdAndCenterId(Long centerTrainerId, Long centerId) {
        CenterTrainer centerTrainer = centerTrainerJpaRepository.findByIdAndCenterId(centerTrainerId, centerId).orElse(null);
        return centerTrainer;
    }

    @Override
    public CenterTrainer findByTrainerIdAndCenterId(Long trainerId, Long centerId) {
        return centerTrainerJpaRepository.findByTrainerIdAndCenterId(trainerId, centerId).orElse(null);
    }
}
