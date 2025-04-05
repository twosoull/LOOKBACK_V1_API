package com.lookback.domain.file.repository;

import com.lookback.domain.file.entity.UploadFile;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository {
    UploadFile findById(UUID id);
    void deleteById(UUID id);
    UploadFile save(UploadFile uploadFile);
}
