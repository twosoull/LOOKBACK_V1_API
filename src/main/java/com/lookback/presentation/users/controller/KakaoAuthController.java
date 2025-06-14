package com.lookback.presentation.users.controller;

import com.lookback.domain.common.constant.enums.UserTypeEnum;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.domain.user.service.LoginService;
import com.lookback.domain.user.service.UserService;
import com.lookback.presentation.users.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/kakao")
@Slf4j
public class KakaoAuthController {

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtUtil jwtUtil;
    private final LoginService loginService;
    private final UserService userService;

    // 1. 로그인 URL 반환
    @GetMapping("/login")
    public ResponseEntity<?> login() {
        String kakaoLoginUrl = "https://kauth.kakao.com/oauth/authorize"
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code";
        return ResponseEntity.ok(kakaoLoginUrl);
    }

    // 2. 카카오 인증 후 콜백
    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        String accessToken = (String) response.getBody().get("access_token");

        // 사용자 정보 요청
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.setBearerAuth(accessToken);
        HttpEntity<String> userInfoRequest = new HttpEntity<>(authHeaders);

        ResponseEntity<Map> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoRequest, Map.class);
        Map<String, Object> kakaoUser = userInfoResponse.getBody();

        // 🔹 카카오 유저 정보 가져오기
        String kakaoId = String.valueOf(kakaoUser.get("id"));
        Map<String, Object> kakaoAccount = (Map<String, Object>) kakaoUser.get("kakao_account");
        String email = kakaoAccount != null ? (String) kakaoAccount.get("email") : null;
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String nickname = profile != null ? (String) profile.get("nickname") : "익명";
        String profileImage = profile != null ? (String) profile.get("profile_image_url") : null;


        // 🔹 유저 존재 여부 확인
        Optional<Users> existingUser = userService.findByKakaoId(kakaoId);

        String isProfileComplete = "N";
        Users user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            isProfileComplete = existingUser.get().getIsProfileComplete();
        } else {
            // ✅ 신규 유저 → 회원가입 처리
            user = loginService.joinFromKakao(kakaoId, email, nickname, profileImage, isProfileComplete);
        }

        //TODO 인가 후 이메일 발급 필요 (users 테이블 비운 후 & 토큰에 이메일 삽입 필요)
        //첫 로그인 사용자 페이지 띄워야 함
        //jwt를 이용해서 사용자 데이터 땡겨와야함

        // 🔹 JWT 발급
        String jwtToken = jwtUtil.getJwtToken(user.getId(), user.getKakaoId(), user.getNickName());
        String refreshToken = jwtUtil.createRefreshToken(user.getKakaoId());
        UserTypeEnum userType = user.getUserType();

        //가입시 초기 값
        if(userType == null){
            userType = UserTypeEnum.MEMBER;
        }

        // JWT 발급 후 반환
        return ResponseEntity.ok(
                Map.of("jwtToken", jwtToken,
                "refreshToken", refreshToken,
                "isProfileComplete", isProfileComplete,
                    "userType",userType.name(),
                    "userId",user.getId()));
    }


}


