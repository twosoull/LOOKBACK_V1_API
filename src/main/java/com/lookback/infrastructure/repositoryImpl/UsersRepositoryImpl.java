package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.infrastructure.repositoryORM.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public List<Users> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public List<Users> findByTrainerYn(String trainerYn) {
        return userJpaRepository.findByTrainerYn(trainerYn);
    }
}
