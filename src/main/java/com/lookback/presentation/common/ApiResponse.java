package com.lookback.presentation.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int status;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse();
        response.status = HttpStatus.OK.value();
        response.data = data;
        return response;
    }
}
