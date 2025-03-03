package com.lookback.domain.user.service;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.presentation.users.dto.UpdateUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INTERNAL_SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<Users> findAllUsers() {

        List<Users> findUsers;
        try {
            findUsers = userRepository.findByTrainerYn("Y");
        } catch (Exception e) {
            throw new RestApiException(INTERNAL_SERVER_ERROR);
        }

        return findUsers;
    }

    public void updateBasicInfo(HttpServletRequest request, UpdateUserInfo updateUserInfo) {

        Users user = (Users) request.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not found");
        }

    }
}
