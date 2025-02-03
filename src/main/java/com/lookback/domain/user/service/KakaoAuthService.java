package com.lookback.domain.user.service;

import com.lookback.domain.user.common.JwtProvider;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.presentation.users.dto.Login.JwtResponse;
import com.lookback.presentation.users.dto.Login.KakaoUser;
import com.lookback.presentation.users.dto.Login.KakaoUserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Transactional
    public JwtResponse processkakoLogin(String kakaoAccessToken) {
        //1. 카카오에서 사용자 정보 가져오기
        KakaoUser kakaoUser = getKakaoUserInfo(kakaoAccessToken);

        //2. DB에서 유저 확인
        Users users = userRepository.findByEmail(kakaoUser.getEmail());

        //3. 신규 유저면 회원 가입 진행
        if(users == null) {
            users = new Users();
            users.setEmail(kakaoUser.getEmail());
            users.setNickName(kakaoUser.getNickName());
            users.setProfileImageUrl(kakaoUser.getProfileImg());

            users = userRepository.save(users);
        }

        //4. JWT 발급 (Access Token && Refresh Token)
        String accessToken = jwtProvider.generateAccessToken(users.getEmail());
        String refreshToken = jwtProvider.generateRefreshToken(users.getEmail());

        //5. Refresh Token을 DB에 저장
        users.setRefreshToken(refreshToken); //영속성 업데이트 탐지

        return new JwtResponse(accessToken, refreshToken);
    }

    public KakaoUser getKakaoUserInfo(String kakaoAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoAccessToken);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserResponse> response =restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                KakaoUserResponse.class
        );

        KakaoUserResponse kakaoUserResponse = response.getBody();
        return new KakaoUser(
                kakaoUserResponse.getKakaoAccount().getEmail(),
                kakaoUserResponse.getProperties().getNickName(),
                kakaoUserResponse.getProperties().getProfileImage()
        );
    }
}
