package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.file.entity.UploadFile;
import com.lookback.domain.file.repository.FileRepository;
import com.lookback.infrastructure.repositoryORM.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepository {

    private final FileJpaRepository fileJpaRepository;

    @Override
    public UploadFile findById(UUID uuid) {
        return fileJpaRepository.findById(uuid).orElse(null);
    }

    @Override
    public void deleteById(UUID uuid) {
        fileJpaRepository.deleteById(uuid);
    }

    @Override
    public UploadFile save(UploadFile uploadFile) {
        return fileJpaRepository.save(uploadFile);
    }
}
