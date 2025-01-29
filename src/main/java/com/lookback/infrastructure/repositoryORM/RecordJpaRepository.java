package com.lookback.infrastructure.repositoryORM;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lookback.domain.record.entity.Record;

import java.util.List;

public interface RecordJpaRepository extends JpaRepository<Record, Long> {

    List<Record> findByUsersIdOrderByCreatedAtDesc(Long usersId);

}
