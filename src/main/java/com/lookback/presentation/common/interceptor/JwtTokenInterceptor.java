package com.lookback.presentation.common.interceptor;

import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.presentation.users.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.Optional;

@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtTokenInterceptor(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing or invalid token");
            return false;
        }

        try {
            // 🔹 JWT에서 사용자 정보 추출
            String token = authorizationHeader.replace("Bearer ", "");
            Claims claims = jwtUtil.extractClaims(request, response, token);
            String kakaoId = (String) claims.get("kakaoId");

            // 🔹 데이터베이스에서 사용자 조회
            Optional<Users> userOptional = userRepository.findByKakaoId(kakaoId);
            if (userOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: User not found");
                return false;
            }

            Users user = userOptional.get();
            request.setAttribute("user", user); // ✅ 요청 속성에 유저 정보 저장

            return true;
        } catch (Exception e) {
            log.debug(e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token");
            return false;
        }
    }
}
