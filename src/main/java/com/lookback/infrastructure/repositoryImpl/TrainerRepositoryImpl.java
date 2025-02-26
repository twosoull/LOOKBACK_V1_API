package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.user.entity.Trainer;
import com.lookback.domain.user.repository.TrainerRepository;
import com.lookback.infrastructure.repositoryORM.TrainerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TrainerRepositoryImpl implements TrainerRepository {

    private final TrainerJpaRepository trainerJpaRepository;

    @Override
    public Trainer findById(Long id) {
        return trainerJpaRepository.findById(id).orElse(null);
    }
}
