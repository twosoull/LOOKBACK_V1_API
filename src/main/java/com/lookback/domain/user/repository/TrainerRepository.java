package com.lookback.domain.user.repository;

import com.lookback.domain.user.entity.Trainer;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository {

    Trainer findById(Long id);
}
