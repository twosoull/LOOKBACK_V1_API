package com.lookback.presentation.file.controller;

import com.lookback.domain.common.constant.enums.FileType;
import com.lookback.domain.file.service.FileService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.file.dto.RequestUploadFile;
import com.lookback.presentation.file.dto.ResponseUploadFile;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<T>> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileType") FileType fileType,
                                                     HttpServletResponse response) {
        ResponseUploadFile upload = fileService.upload(file, fileType);
        return new ResponseEntity(ApiResponse.success(upload, response), HttpStatus.OK);
    }

}
