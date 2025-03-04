package com.lookback.common.context;

import com.lookback.domain.user.entity.Users;
import jakarta.servlet.http.HttpServletRequest;

public class UserContext {
    public static Users getUser(HttpServletRequest request) {
        Users users = (Users) request.getAttribute("user");
        if (users == null) {
            throw new RuntimeException("User not found");
        }
        return users;
    }
}
