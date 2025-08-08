package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.manager.center.entity.CenterTrainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterTrainerJpaRepository extends JpaRepository<CenterTrainer, Long> {
    Page<CenterTrainer> findByCenterId(Long centerId, Pageable pageable);
}
