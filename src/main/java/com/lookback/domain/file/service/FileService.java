package com.lookback.domain.file.service;

import com.lookback.domain.common.constant.enums.FileStatus;
import com.lookback.domain.common.constant.enums.FileType;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.file.entity.UploadFile;
import com.lookback.domain.file.policy.FilePolicy;
import com.lookback.domain.file.repository.FileRepository;
import com.lookback.presentation.file.dto.ResponseUploadFile;
import com.lookback.presentation.record.dto.UploadFileDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.FILE_UPLOAD_FAIL;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.upload-path}")
    private String uploadDir;
    @Value("${file.folder-path}")
    private String folderPath;

    private final FileRepository fileRepository;

    /**
     * 파일 저장 후, referenceId 매칭
     * @param uploadFileUUID
     * @param referenceId
     * @param ord
     * @param fileType
     */
    @Transactional
    public void linkToReferenceId(UUID uploadFileUUID, Long referenceId, Long ord, FileType fileType) {
            UploadFile findFile = fileRepository.findById(uploadFileUUID);
            findFile.setReferenceId(referenceId);
            findFile.setOrd(ord);
            // fileType은 이미지를 저장할 때에 넣기 때문에 굳이 넣을 필요는 없으나, 변경 시 필요
            if (ObjectUtils.isNotEmpty(fileType)) {
                findFile.setType(fileType);
            }
            findFile.setStatus(FileStatus.SAVED);
    }

    /**
     * 파일 저장 후, referenceId 매칭 List일 경우 사용
     * @param uploadFileDtos
     * @param referenceId
     * @param fileType
     */
    @Transactional
    public void linkToReferenceIdList(List<UploadFileDto> uploadFileDtos, Long referenceId, FileType fileType) {
        if (ObjectUtils.isNotEmpty(uploadFileDtos)) {
            uploadFileDtos.stream()
                    .filter(Objects::nonNull) // null 방어
                    .forEach(ufd -> this.linkToReferenceId(
                            ufd.getUuid(),
                            referenceId,
                            ufd.getOrd(),
                            fileType
                    ));
        }
    }

    /**
     * 파일 삭제, 파일 Delete로 변경, 이후 batch에서 지워짐
     * @param uploadFileUUID
     */
    public void deleteToReferenceId(UUID uploadFileUUID) {
        UploadFile findFile = fileRepository.findById(uploadFileUUID);
        findFile.setStatus(FileStatus.DELETE);
    }

    /**
     * 파일 삭제, 파일 Delete로 변경, 이후 batch에서 지워짐 List 사용
     * @param deleteFileDtos
     */
    public void deleteToRefernceIdList(List<UploadFileDto> deleteFileDtos) {
        if (ObjectUtils.isNotEmpty(deleteFileDtos)) {
            deleteFileDtos.stream()
                    .filter(Objects::nonNull) // null 방어
                    .forEach(delFile -> this.deleteToReferenceId(
                            delFile.getUuid()
                    ));
        }
    }

    /**
     * 실제 저장 메소드
     * @param file
     * @param fileType
     * @return
     */
    public ResponseUploadFile upload(MultipartFile file, FileType fileType) {
        FilePolicy.upload(fileType, file);

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1) : "";
        String fileName = UUID.randomUUID().toString();
        String fullPath  = uploadDir + "/" + fileName + "." + extension;
        String relativePath = folderPath + "/" + fileName + "." + extension;
        Long size = file.getSize();

        // ✅ 폴더가 없으면 만든다
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();  // 상위 폴더까지 전부 생성
            if (!created) {
                try {
                    throw new IOException("업로드 폴더 생성 실패: " + uploadDir);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //디스크 저장
        File target = new File(fullPath);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            throw new RestApiException(FILE_UPLOAD_FAIL);
        }
        UploadFile uploadFile = UploadFile.of(fileType, originalFilename, fileName, fullPath, extension, relativePath, size, FileStatus.TEMP);
        UploadFile savedUploadFile = fileRepository.save(uploadFile);

        return ResponseUploadFile.fromEntity(savedUploadFile);
    }
}
