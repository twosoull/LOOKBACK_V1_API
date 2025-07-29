package com.lookback.common.config;

import com.lookback.presentation.common.interceptor.JwtTokenInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenInterceptor jwtTokenInterceptor;

    // application.yml의 "upload.path" 값을 주입
    @Value("${file.upload-path}")
    private String uploadPath;

    public WebConfig(JwtTokenInterceptor jwtTokenInterceptor) {
        this.jwtTokenInterceptor = jwtTokenInterceptor;
    }
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000", "http://211.188.52.141:3000", "http://pctup.com", "https://pctup.com")); // 프론트엔드 주소 지정
        config.setAllowedHeaders(List.of("*")); // Authorization 헤더 허용
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // OPTIONS 허용
        config.setMaxAge(3600L); // Preflight 결과 캐싱 (1시간)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }




   @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)
                .excludePathPatterns(
                        "/admin/**",
                        "/auth/**",
                        "/public/**",
                        "/mock/**",
                        "/resources/**",
                        "/static/**",
                        "/uploads/**",
                        "/testLogin"); // ✅ 로그인, 회원가입 관련 API는 제외
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /images/** 로 들어오는 요청 → "file:업로드경로/" 에서 파일 탐색
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath + "/");
        // 예: file:/Users/iyeonghun/Desktop/PROJECT/image/
        // 배포환경: file:/home/ec2-user/app/images/
    }
}