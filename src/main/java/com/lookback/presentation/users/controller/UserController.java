package com.lookback.presentation.users.controller;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.service.TrainingService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import com.lookback.presentation.users.dto.FindTrainingUsersResponse;
import com.lookback.presentation.users.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final TrainingService trainingService;

    @GetMapping("/mockTest/exception")
    public void exceptionTest() {
        throw new RestApiException(INVALID_PARAMETER);
    }

    @GetMapping("/mockTest/test")
    public ResponseEntity<ApiResponse<T>> Test() {
        return new ResponseEntity(ApiResponse.success(new UsersDto()), HttpStatus.OK);
    }

}
