package com.lookback.domain.user.service;

import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;

    public Users joinFromKakao(String kakaoId, String email, String nickname, String profileImage, String isProfileComplete) {
        Users user;
        user = Users.builder()
                .kakaoId(kakaoId)
                .email(email)
                .nickName(nickname)
                .profileImageUrl(profileImage)
                .isProfileComplete(isProfileComplete)
                .build();
        userRepository.save(user);
        log.info("✅ 신규 회원 가입 완료: {}" , nickname);
        return user;
    }

}
