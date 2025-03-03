package com.lookback.presentation.users.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final SecretKey accessKey;
    private final SecretKey refreshKey;
    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60; // ✅ 1시간
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // ✅ 7일


    /**
     * 생성자 주입 방식으로 secretKey 설정
     */
    public JwtUtil(@Value("${jwt.secret-key}") String secretKey,
                   @Value("${jwt.refresh-secret-key}") String refreshSecretKey) {
        this.accessKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.refreshKey = Keys.hmacShaKeyFor(refreshSecretKey.getBytes());
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
     * JWT 토큰에서 정보 추출
     */
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * JWT 토큰 검증 (static 제거)
     */
    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("토큰 만료: " + e.getMessage());
        } catch (JwtException e) {
            System.out.println("토큰 검증 실패: " + e.getMessage());
        }
        return false;
    }
}
