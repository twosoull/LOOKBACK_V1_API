package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
    List<Users> findByTrainerYn(@Param("trainerYn") String trainerYn);
}
