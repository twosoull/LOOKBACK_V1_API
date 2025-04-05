package com.lookback.domain.file.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.FileType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UploadFile extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "FILE_ID")
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private FileType type;
    private Long referenceId; // ex: 사용자 ID, 운동 기록 ID 등

    private String originalName;
    private String fileName;
    private String fullPath;
    private String extension;
    private String relativePath;
    private Long ord;
    private Long size;

    public static UploadFile of(FileType type, String originalName, String fileName, String fullPath, String extension,String relativePath, Long size) {
        UploadFile file = new UploadFile();
        file.setType(type);
        file.setOriginalName(originalName);
        file.setFileName(fileName);
        file.setFullPath(fullPath);
        file.setExtension(extension);
        file.setRelativePath(relativePath);
        file.setSize(size);
        return file;
    }
}
