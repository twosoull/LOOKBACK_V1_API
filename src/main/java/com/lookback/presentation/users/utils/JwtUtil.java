package com.lookback.presentation.users.utils;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.REFRESH_TOKEN_EXPIRED;

@Component
public class JwtUtil {

    private final SecretKey accessKey;
    private final SecretKey refreshKey;
    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 ;
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // ✅ 7일
    private final UserRepository userRepository;

    /**
     * 생성자 주입 방식으로 secretKey 설정
     */
    public JwtUtil(@Value("${jwt.secret-key}") String secretKey,
                   @Value("${jwt.refresh-secret-key}") String refreshSecretKey,
                   UserRepository userRepository) {
        this.accessKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.refreshKey = Keys.hmacShaKeyFor(refreshSecretKey.getBytes());
        this.userRepository = userRepository;
    }

     /**
     * JWT 토큰 생성 (static 제거)
     */
    public String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims) // 사용자 정보 (예: id, email)
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION)) // 만료 시간
                .signWith(accessKey, SignatureAlgorithm.HS256) // 서명
                .compact();
    }

    /**
     * Access Token 생성 (kakaoId)
     */
    public String createAccessToken(String kakaoId) {
        return Jwts.builder()
                .setSubject(kakaoId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION)) // ✅ 15분 만료
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Refresh Token 생성
     */
    public String createRefreshToken(String kakaoId) {
        return Jwts.builder()
                .setSubject(kakaoId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Access Token이 만료되었을 때 Refresh Token 검증 후 새로운 Access Token 발급
     */
    private Claims handleExpiredAccessToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String refreshToken = request.getHeader("Refresh-Token"); // ✅ 클라이언트가 Refresh Token을 보내야 함

            if (refreshToken == null || refreshToken.isEmpty()) {
                throw new RestApiException(REFRESH_TOKEN_EXPIRED);
            }

            // ✅ Refresh Token 검증
            Claims refreshClaims = Jwts.parserBuilder()
                    .setSigningKey(refreshKey)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            String kakaoId = refreshClaims.getSubject(); // ✅ Refresh Token의 subject(사용자 ID) 가져오기

            // ✅ DB에서 사용자 정보 조회 (Refresh Token이 저장된 경우)
            Optional<Users> userOptional = userRepository.findByKakaoId(kakaoId);
            if (userOptional.isEmpty()) {
                throw new RuntimeException("해당 사용자를 찾을 수 없습니다.");
            }

            Users user = userOptional.get();

            // ✅ 새로운 Access Token 발급
            String newAccessToken = createAccessToken(user.getKakaoId());

            // ✅ 새로운 Refresh Token 발급 (보안을 위해)
            String newRefreshToken = createRefreshToken(user.getKakaoId());

            // ✅ 응답 헤더에 새로운 Access Token & Refresh Token 추가
            response.setHeader("New-Access-Token", newAccessToken);
            response.setHeader("New-Refresh-Token", newRefreshToken);

            // ✅ Refresh Token 검증
            return Jwts.parserBuilder()
                    .setSigningKey(accessKey)
                    .build()
                    .parseClaimsJws(newAccessToken)
                    .getBody(); // ✅ 새 토큰이 발급된 경우 기존 Refresh Token의 Claims 반환
        } catch (ExpiredJwtException e) {
            throw new RestApiException(REFRESH_TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 Refresh Token입니다.", e);
        }
    }


    /**
     * JWT 토큰에서 정보 추출 (유효시간 추출)
     */
    public Claims extractClaims(HttpServletRequest request, HttpServletResponse response, String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(accessKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // ✅ Access Token 만료 → Refresh Token 확인 후 Access Token 재발급
            return handleExpiredAccessToken(request, response);
        } catch (JwtException ex) {
            throw new RuntimeException("유효하지 않은 토큰입니다.", ex);
        }
    }

    /**
     * JWT 토큰 검증 (static 제거)
     */
/*    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("토큰 만료: " + e.getMessage());
        } catch (JwtException e) {
            System.out.println("토큰 검증 실패: " + e.getMessage());
        }
        return false;
    }*/


}
