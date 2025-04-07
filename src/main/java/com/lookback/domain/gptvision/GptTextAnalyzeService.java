package com.lookback.domain.gptvision;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class GptTextAnalyzeService {

    @Value("${gpt.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String analyzeImageWithGpt4o(MultipartFile image) throws IOException {
        // 1. Base64 인코딩
        byte[] imageBytes = image.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        String imageUrl = "data:image/jpeg;base64," + base64Image;

        // 2. 프롬프트 작성
        Map<String, Object> imagePart = Map.of(
                "type", "image_url",
                "image_url", Map.of("url", imageUrl)
        );

        Map<String, Object> textPart = Map.of(
                "type", "text",
                "text", "이 음식 사진을 분석해서 음식명, 칼로리, 탄수화물, 단백질, 지방을 JSON으로 알려줘."
        );

        Map<String, Object> message = Map.of(
                "role", "user",
                "content", List.of(textPart, imagePart)
        );

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o",
                "messages", List.of(message),
                "max_tokens", 500
        );

        // 3. API 요청
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

        return response.getBody();
    }
}