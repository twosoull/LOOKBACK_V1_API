package com.lookback.domain.record.dto;

import com.lookback.domain.common.constant.enums.FileStatus;
import com.lookback.domain.common.constant.enums.FileType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UploadFileDomainDto {

    private UUID uuid;
    private FileType type;
    private Long referenceId; // ex: 사용자 ID, 운동 기록 ID 등
    private String originalName;
    private String fileName;
    private String fullPath;
    private String extension;
    private String relativePath;
    private Long ord;
    private Long size;
    private FileStatus status;

    public UploadFileDomainDto(UUID uuid, FileType type, Long referenceId, String fullPath, String extension, String originalName, String fileName, String relativePath, Long ord, Long size, FileStatus status) {
        this.uuid = uuid;
        this.type = type;
        this.referenceId = referenceId;
        this.fullPath = fullPath;
        this.extension = extension;
        this.originalName = originalName;
        this.fileName = fileName;
        this.relativePath = relativePath;
        this.ord = ord;
        this.size = size;
        this.status = status;
    }
}
