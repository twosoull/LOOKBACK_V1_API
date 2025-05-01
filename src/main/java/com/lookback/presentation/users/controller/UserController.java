package com.lookback.presentation.users.controller;

import com.lookback.domain.common.constant.enums.UserTypeEnum;
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
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<T>> user(UsersDto usersDto, HttpServletResponse response) {
        return new ResponseEntity(ApiResponse.success(userService.findById(usersDto), response), HttpStatus.OK);
    }

}
