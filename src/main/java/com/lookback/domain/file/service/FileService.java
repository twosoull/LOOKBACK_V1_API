package com.lookback.domain.file.service;

import com.lookback.domain.common.constant.enums.FileStatus;
import com.lookback.domain.common.constant.enums.FileType;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.file.entity.UploadFile;
import com.lookback.domain.file.policy.FilePolicy;
import com.lookback.domain.file.repository.FileRepository;
import com.lookback.presentation.file.dto.ResponseUploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
