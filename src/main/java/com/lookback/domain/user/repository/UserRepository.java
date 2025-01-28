package com.lookback.domain.user.repository;

import com.lookback.domain.user.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<Users> findAll();

    List<Users> findByTrainerYn(String trainerYn);
}
