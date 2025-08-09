package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.manager.center.entity.CenterTrainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CenterTrainerJpaRepository extends JpaRepository<CenterTrainer, Long> {
    Page<CenterTrainer> findByCenterId(Long centerId, Pageable pageable);

    Optional<CenterTrainer> findByIdAndCenterId(Long id, Long centerId);

    Optional<CenterTrainer>  findByTrainerIdAndCenterId(Long trainerId, Long centerId);
}
