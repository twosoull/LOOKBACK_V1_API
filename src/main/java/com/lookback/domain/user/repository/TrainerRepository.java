package com.lookback.domain.user.repository;

import com.lookback.domain.user.entity.Trainer;
import com.lookback.domain.user.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository {

    Trainer findById(Long id);

    Trainer findByUsersId(Long trainerId);

    Trainer save(Trainer trainer);
}
