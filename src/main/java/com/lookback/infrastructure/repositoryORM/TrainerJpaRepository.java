package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.user.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerJpaRepository extends JpaRepository<Trainer, Long> {
    Trainer findByUserId(Long trainerId);
}
