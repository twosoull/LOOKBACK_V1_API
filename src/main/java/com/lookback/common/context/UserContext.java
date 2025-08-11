package com.lookback.common.context;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.entity.Users;
import com.lookback.presentation.users.dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.NO_LOGIN_USER;

public class UserContext {
    public static UsersDto getUser(HttpServletRequest request) {
        UsersDto users = (UsersDto) request.getAttribute("user");
        if (users == null) {
            throw new RestApiException(NO_LOGIN_USER);
        }
        return users;
    }
}
