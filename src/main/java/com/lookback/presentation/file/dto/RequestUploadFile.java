package com.lookback.presentation.file.dto;

import com.lookback.domain.common.constant.enums.FileType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RequestUploadFile {

    private MultipartFile file;
    private FileType fileType;

}
