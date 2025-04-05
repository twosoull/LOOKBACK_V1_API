package com.lookback.presentation.file.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UploadFileDto {

    private UUID uuid;

    private String fileType;
    private Long referenceId; // ex: 사용자 ID, 운동 기록 ID 등

    private String originalName;
    private String fileName;
    private String fullPath;
    private String extension;
    private String relativePath;
    private Long ord;
    private Long size;

}
