package com.lookback.domain.manager.center.repository;

import com.lookback.domain.manager.center.entity.CenterTrainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterTrainerRepository {
    // 목록 조회
    Page<CenterTrainer> findByCenterId(Long centerId, Pageable pageable);

    // 상세 조회
    CenterTrainer findByIdAndCenterId(Long centerId, Long centerTrainerId);

    CenterTrainer findByTrainerIdAndCenterId(Long trainerId, Long centerId);
}
