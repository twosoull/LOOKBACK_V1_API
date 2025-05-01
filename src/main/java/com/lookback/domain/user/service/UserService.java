package com.lookback.domain.user.service;

import com.lookback.common.context.UserContext;
import com.lookback.domain.common.constant.enums.UserTypeEnum;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.entity.Trainer;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.presentation.users.dto.UpdateUserInfo;
import com.lookback.presentation.users.dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INTERNAL_SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TrainingService trainingService;

    @Transactional
    public UsersDto updateBasicInfo(HttpServletRequest request, UpdateUserInfo updateUserInfo) {
        Users findUser;

        try {
            UsersDto user = UserContext.getUser(request);
            findUser = userRepository.findById(user.getUserId());
            if (findUser == null) {
                throw new RestApiException(INTERNAL_SERVER_ERROR);
            }

            findUser.setWeight(Double.parseDouble(updateUserInfo.getWeight()));
            findUser.setHeight(Double.parseDouble(updateUserInfo.getHeight()));
            findUser.setUserType(updateUserInfo.getUserType().equals("TRAINER") ?
                             UserTypeEnum.TRAINER : UserTypeEnum.MEMBER);
            findUser.setIsProfileComplete("Y");

            // 트레이너면 트레이너 등록
            if (updateUserInfo.getUserType().equals("TRAINER")) {
                Trainer trainer = new Trainer();
                trainer.setUser(findUser);
                trainingService.save(trainer);
            }

        } catch (Exception e) {
            throw new RestApiException(INTERNAL_SERVER_ERROR);
        }

        return UsersDto.fromEntity(findUser);
    }

    public UsersDto findById(UsersDto usersDto) {

        if (usersDto == null || usersDto.getUserId() == null || usersDto.getUserId() == 0) {
            throw new RestApiException(INTERNAL_SERVER_ERROR);
        }

        Users findUser = userRepository.findById(usersDto.getUserId());

        if( findUser == null ) {
            return null;
        }
        return UsersDto.fromEntity(findUser);
    }

    public Optional<Users> findByKakaoId(String kakaoId) {
        return userRepository.findByKakaoId(kakaoId);
    }
}
