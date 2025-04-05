package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.file.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileJpaRepository extends JpaRepository<UploadFile, UUID> {


}
