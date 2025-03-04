package com.lookback.presentation.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int status;
    private T result;
    private String newAccessToken;
    private String newRefreshToken;

    // 성공 응답을 생성하는 메서드
    public static <T> ApiResponse<T> success(T result, HttpServletResponse response) {
        return new ApiResponse<>(HttpStatus.OK.value()
                , result
                , response.getHeader("New-Access-Token")
                , response.getHeader("New-Refresh-Token"));
    }
}
