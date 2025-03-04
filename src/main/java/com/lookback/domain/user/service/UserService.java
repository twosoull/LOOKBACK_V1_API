package com.lookback.domain.user.service;

import com.lookback.common.context.UserContext;
import com.lookback.domain.common.constant.enums.UserTypeEnum;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.presentation.users.dto.UpdateUserInfo;
import com.lookback.presentation.users.dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INTERNAL_SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UsersDto updateBasicInfo(HttpServletRequest request, UpdateUserInfo updateUserInfo) {
        Users findUser;

        try {
            Users user = UserContext.getUser(request);
            findUser = userRepository.findById(user.getId());
            if (findUser == null) {
                throw new RestApiException(INTERNAL_SERVER_ERROR);
            }

            findUser.setWeight(Double.parseDouble(updateUserInfo.getWeight()));
            findUser.setHeight(Double.parseDouble(updateUserInfo.getHeight()));
            findUser.setUserType(updateUserInfo.getUserType().equals("TRAINER") ?
                             UserTypeEnum.TRAINER : UserTypeEnum.MEMBER);
            findUser.setIsProfileComplete("Y");

        } catch (Exception e) {
            throw new RestApiException(INTERNAL_SERVER_ERROR);
        }

        return UsersDto.fromEntity(findUser);
    }

}
