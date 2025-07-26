package com.lookback.presentation.admin.controller;

import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.exercise.dto.FindExercisesResponse;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {

    public ResponseEntity<ApiResponse<T>> login(String username, String password) {
        // 토큰 쿠키 사용 로그인 작업
        return null;
    }

}
