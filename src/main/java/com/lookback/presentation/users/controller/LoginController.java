package com.lookback.presentation.users.controller;

import com.lookback.domain.common.constant.enums.UserTypeEnum;
import com.lookback.domain.user.service.UserService;
import com.lookback.presentation.users.dto.UsersDto;
import com.lookback.presentation.users.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final JwtUtil jwtUtil;
    private final UserService userService;


    // 2. 카카오 인증 후 콜백
    @GetMapping("/testLogin")
    public ResponseEntity<?> login(UsersDto usersDto) {

        // 🔹 유저 존재 여부 확인
        UsersDto existingUser = userService.findById(usersDto);
        String uuid = UUID.randomUUID().toString();
        // 🔹 JWT 발급
        String jwtToken = jwtUtil.getJwtToken(existingUser.getUserId(), uuid, existingUser.getNickName());
        String refreshToken = jwtUtil.createRefreshToken(uuid);
        UserTypeEnum userType = UserTypeEnum.valueOf(existingUser.getUserType());

        //가입시 초기 값
        if(userType == null){
            userType = UserTypeEnum.MEMBER;
        }

        // JWT 발급 후 반환
        return ResponseEntity.ok(
                Map.of("jwtToken", jwtToken,
                        "refreshToken", refreshToken,
                        "isProfileComplete", "Y",
                        "userType",userType.name(),
                        "userId",existingUser.getUserId()));
    }

}
