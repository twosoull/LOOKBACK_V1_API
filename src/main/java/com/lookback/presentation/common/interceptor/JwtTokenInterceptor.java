package com.lookback.presentation.common.interceptor;

import com.lookback.common.utils.CommonUtil;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.domain.user.service.UserService;
import com.lookback.presentation.users.dto.UsersDto;
import com.lookback.presentation.users.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.Optional;

@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserService userService;

    @Value("${token.expiration.access}")
    private long ACCESS_TOKEN_EXPIRATION;

    @Value("${token.expiration.refresh}")
    private long REFRESH_TOKEN_EXPIRATION;


    public JwtTokenInterceptor(JwtUtil jwtUtil, UserRepository userRepository, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String cookieHeader = request.getHeader("Cookie");
        System.out.println("Cookie Header: " + cookieHeader);

        String token = extractTokenFromCookie(request, "accessToken");
        // 🔸 Access Token이 만료된 경우 → Refresh Token 검증 시도
        String refreshToken = extractTokenFromCookie(request, "refreshToken");

        // 🔹 Access Token 유효할 경우 그대로 통과
        if (token != null && jwtUtil.validateToken(token)) {
            return injectUserFromToken(request, response, token);
        }

        if (refreshToken != null && jwtUtil.validateRefreshToken(refreshToken)) {
            try {
                // 🔹 Refresh 토큰에서 유저 정보 추출
                Claims claims = jwtUtil.extractRefreshKeyAllClaims(refreshToken);
                String kakaoId = (String) claims.getSubject(); // TODO kakaoId

                Optional<Users> optionalUser = userService.findByKakaoId(kakaoId);
                if (optionalUser.isEmpty()) {
                    throw new RuntimeException("User not found");
                }

                Users user = optionalUser.get();

                // 🔹 새로운 AccessToken 재발급
                String newAccessToken = jwtUtil.getJwtToken(user.getId(), user.getKakaoId(), user.getNickName());
                String newRefreshToken = jwtUtil.createRefreshToken(user.getKakaoId());


                // TODO secure 달기
                ResponseCookie newAccessCookie = ResponseCookie.from("accessToken", newAccessToken)
                        .httpOnly(true).secure(CommonUtil.isLocalProfile()? false : true).path("/").sameSite("Lax").maxAge(ACCESS_TOKEN_EXPIRATION).build();

                ResponseCookie newRefreshCookie = ResponseCookie.from("refreshToken", newRefreshToken)
                        .httpOnly(true).secure(CommonUtil.isLocalProfile()? false : true).path("/").sameSite("Lax").maxAge(REFRESH_TOKEN_EXPIRATION).build();

                response.setHeader("Set-Cookie", newAccessCookie.toString());

                // ✅ 재발급된 토큰을 기반으로 유저 정보를 다시 inject
                return injectUserFromToken(request, response, newAccessToken);
            } catch (Exception e) {
                log.debug("Refresh token failed: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"message\": \"로그인이 필요합니다.\"}");
                return false;
            }
        }

        // 🔸 AccessToken & RefreshToken 모두 없거나 유효하지 않음
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"message\": \"로그인이 필요합니다.\"}");
        return false;
    }

    private String extractTokenFromCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private boolean injectUserFromToken(HttpServletRequest request, HttpServletResponse response, String token) {
        try {
            Claims claims = jwtUtil.extractClaims(request, response, token);
            Integer idInt = (Integer) claims.get("id");
            Long id = idInt.longValue();

            UsersDto findUsersDto = userService.findById(
                    UsersDto.builder().userId(id).build()
            );

            if (findUsersDto == null) {
                return false;
            }

            request.setAttribute("user", findUsersDto);
            return true;
        } catch (Exception e) {
            log.debug("User injection failed: " + e.getMessage());
            return false;
        }
    }

}
