package com.lookback.common.context;

import com.lookback.domain.user.entity.Users;
import com.lookback.presentation.users.dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;

public class UserContext {
    public static UsersDto getUser(HttpServletRequest request) {
        UsersDto users = (UsersDto) request.getAttribute("user");
        if (users == null) {
            throw new RuntimeException("User not found");
        }
        return users;
    }
}
