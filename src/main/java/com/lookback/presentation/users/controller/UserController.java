package com.lookback.presentation.users.controller;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.service.TrainingService;
import com.lookback.domain.user.service.UserService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import com.lookback.presentation.users.dto.FindTrainingUsersResponse;
import com.lookback.presentation.users.dto.UpdateUserInfo;
import com.lookback.presentation.users.dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final TrainingService trainingService;
    private final UserService userService;

    @PostMapping("/user/updateBasicInfo")
    public ResponseEntity<ApiResponse<T>> updateBasicInfo(HttpServletRequest request
            , HttpServletResponse response, @RequestBody UpdateUserInfo updateUserInfo) {
        return new ResponseEntity(ApiResponse.success(
                userService.updateBasicInfo(request, updateUserInfo)
                ,response
        ), HttpStatus.OK);
    }
/*
    @GetMapping("/mockTest/test")
    public ResponseEntity<ApiResponse<T>> Test() {
        return new ResponseEntity(ApiResponse.success(new UsersDto()), HttpStatus.OK);
    }
*/
}
