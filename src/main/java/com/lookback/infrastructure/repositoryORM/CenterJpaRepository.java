package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.manager.center.entity.Center;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CenterJpaRepository extends JpaRepository<Center, Long> {

}
