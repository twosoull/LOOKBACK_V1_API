package com.lookback.domain.excel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            // 임시 파일 생성
            File tempFile = File.createTempFile("uploaded", ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(file.getBytes());
            }

            // 엑셀 데이터 저장 로직 호출
            excelService.importDataFromExcel(tempFile.getAbsolutePath());

            // 임시 파일 삭제
            tempFile.delete();

            return ResponseEntity.ok("Excel data imported successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그 출력
            return ResponseEntity.status(500).body("Failed to import Excel data: " + e.getMessage());
        }
    }
}
