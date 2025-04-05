package com.lookback.domain.file.policy;

import com.lookback.domain.common.constant.enums.FileType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public class FilePolicy {

    public static void upload(FileType fileType, MultipartFile file) {
        hasFileType(fileType);
        hasFile(file);
    }

    public static void hasFileType(FileType fileType) {
        if (fileType == null) {
            throw new IllegalArgumentException("fileType is null");
        }
    }

    public static void hasFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("file is null or empty");
        }
        if (file.getSize() <= 0) {
            throw new IllegalArgumentException("fileSize is invalid");
        }
    }

}
