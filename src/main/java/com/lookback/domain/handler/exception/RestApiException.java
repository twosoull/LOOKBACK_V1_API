package com.lookback.domain.handler.exception;
import com.lookback.domain.handler.response.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{ //언체크 예외만 상속

    private final ErrorCode errorCode;

}