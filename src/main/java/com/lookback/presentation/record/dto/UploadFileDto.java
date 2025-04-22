package com.lookback.presentation.record.dto;

import com.lookback.domain.common.constant.enums.FileStatus;
import com.lookback.domain.common.constant.enums.FileType;
import com.lookback.domain.file.entity.UploadFile;
import com.lookback.domain.record.dto.UploadFileDomainDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileDto {

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

    public static UploadFileDto of(UUID uuid, FileType type, Long referenceId, String originalName, String fileName, String fullPath, String extension, String relativePath, Long ord, Long size, FileStatus status) {
        return UploadFileDto.builder()
                .uuid(uuid)
                .type(type)
                .referenceId(referenceId)
                .originalName(originalName)
                .fileName(fileName)
                .fullPath(fullPath)
                .extension(extension)
                .relativePath(relativePath)
                .ord(ord)
                .size(size)
                .status(status)
                .build();
    }

    public static List<UploadFileDto> listOf(List<UploadFileDomainDto> uploadFileDomainDtos) {
        return  uploadFileDomainDtos != null ?
                uploadFileDomainDtos.stream().map(uf -> UploadFileDto.of(
                uf.getUuid(),
                uf.getType(),
                uf.getReferenceId(),
                uf.getOriginalName(),
                uf.getFileName(),
                uf.getFullPath(),
                uf.getExtension(),
                uf.getRelativePath(),
                uf.getOrd(),
                uf.getSize(),
                uf.getStatus()
        )).toList() : null;
    }
}
