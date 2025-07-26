package com.lookback.presentation.common.interceptor;

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

    public JwtTokenInterceptor(JwtUtil jwtUtil, UserRepository userRepository, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 쿠키에서 accessToken 추출
        String token = extractTokenFromCookie(request, "accessToken");

        if (token == null || !jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"로그인이 필요합니다.\"}");
            return false;
        }

        try {
            // 🔹 JWT에서 사용자 정보 추출
            Claims claims = jwtUtil.extractClaims(request, response, token);
            Integer idInt = (Integer) claims.get("id");
            Long id = idInt.longValue();

            // 🔹 데이터베이스에서 사용자 조회
            UsersDto build = UsersDto.builder().userId(id).build();
            UsersDto findUsersDto = userService.findById(build);

            if (findUsersDto == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: User not found");
                return false;
            }

            request.setAttribute("user", findUsersDto); // ✅ 요청 속성에 유저 정보 저장

            return true;
        } catch (Exception e) {
            log.debug(e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token");
            return false;
        }
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
}
