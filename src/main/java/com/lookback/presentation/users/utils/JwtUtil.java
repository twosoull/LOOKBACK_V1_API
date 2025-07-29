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
    @Value("${token.expiration.access}")
    private long ACCESS_TOKEN_EXPIRATION;

    @Value("${token.expiration.refresh}")
    private long REFRESH_TOKEN_EXPIRATION;

    private final UserRepository userRepository;


    /**
     * jwtToken 생성 메소드
     * @param id, key(현재 snsId로 사용, 일반유저는 random key 값), name
     * @return
     */
    public String getJwtToken(Long id, String key, String name) {
        String jwtToken = this.createToken(Map.of(
                "id", id,
                "key", key,
                "name", name
        ));
        return jwtToken;
    }

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
     * @param key(현재 snsId로 사용, 일반유저는 random key 값)
     * @return
     */
    public String createAccessToken(String key) {
        return Jwts.builder()
                .setSubject(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION)) // ✅ 15분 만료
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Refresh Token 생성
     * @param key(현재 snsId로 사용, 일반유저는 random key 값)
     * @return
     */
    public String createRefreshToken(String key) {
        return Jwts.builder()
                .setSubject(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();
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
        } catch (JwtException ex) {
            throw new RuntimeException("유효하지 않은 토큰입니다.", ex);
        }
    }

    public Claims extractRefreshKeyAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(refreshKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateRefreshToken(String token) {
        try {
            Claims claims = extractRefreshKeyAllClaims(token);
            // 필요한 검증 추가 가능 (예: 블랙리스트)
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateToken(String token) {
        try {
            // JWT 검증
            Jwts.parser().setSigningKey(accessKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(accessKey).parseClaimsJws(token).getBody();
        return claims.getSubject(); // 또는 claims.get("userId")
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
