package com.lookback.presentation.file.dto;

import com.lookback.domain.file.entity.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseUploadFile {

    private String uuid;
    private Long referencedId;
    private String originalName;
    private String fileName;
    private String fullPath;
    private String extension;
    private String relativePath;
    private Long ord;

    public static ResponseUploadFile fromEntity(UploadFile uploadFile) {
        return ResponseUploadFile.builder()
                .uuid(uploadFile.getUuid().toString())
                .referencedId(uploadFile.getReferenceId())
                .originalName(uploadFile.getOriginalName())
                .fileName(uploadFile.getFileName())
                .fullPath(uploadFile.getFullPath())
                .extension(uploadFile.getExtension())
                .relativePath(uploadFile.getRelativePath())
                .ord(uploadFile.getOrd())
                .build();
    }
}
