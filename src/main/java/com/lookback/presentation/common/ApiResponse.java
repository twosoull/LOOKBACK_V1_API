package com.lookback.presentation.common;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    // 성공 응답을 생성하는 메서드
    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(HttpStatus.OK.value(), result);
    }
}
