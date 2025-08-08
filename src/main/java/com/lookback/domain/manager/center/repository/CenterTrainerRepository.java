package com.lookback.domain.manager.center.repository;

import com.lookback.domain.manager.center.entity.CenterTrainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterTrainerRepository {
    Page<CenterTrainer> findByCenterId(Long centerId, Pageable pageable);

}
