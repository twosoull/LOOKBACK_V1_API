package com.lookback.infrastructure.repositoryORM;

import com.lookback.infrastructure.repositoryCustom.RecordRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lookback.domain.record.entity.Record;

import java.util.List;
import java.util.Optional;

public interface RecordJpaRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom {

    List<Record> findByUsersIdOrderByCreatedAtDesc(Long usersId);

    List<Record> findByUsersIdAndTrainingIdIsNotNullOrderByCreatedAtDesc(Long usersId);

    List<Record> findByUsersIdAndTrainingIdIsNullOrderByCreatedAtDesc(Long usersId);

    List<Record> findByUsersIdOrderByRecordDateDesc(Long usersId, String category);
}
