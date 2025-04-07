package com.lookback.presentation.gptvsion;

import com.lookback.domain.gptvision.GptTextAnalyzeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/diet-text")
@RequiredArgsConstructor
public class GptTextAnalyzeController {

    private final GptTextAnalyzeService service;


    @PostMapping
    public ResponseEntity<?> analyzeImage(@RequestParam("image") MultipartFile image) {
        try {
            String result = service.analyzeImageWithGpt4o(image);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("분석 실패: " + e.getMessage());
        }
    }
}