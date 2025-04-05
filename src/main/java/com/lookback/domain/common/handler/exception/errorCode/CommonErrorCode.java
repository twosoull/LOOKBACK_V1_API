package com.lookback.domain.common.handler.exception.errorCode;

import com.lookback.domain.common.handler.response.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, "파라미터 확인이 필요합니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, "조회된 값이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    UNABLE_CALCULATE_ORDER(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "대기열 순서를 찾을 수 없습니다."),
    NEGATIVE_VALUE(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "대기열 순서를 찾을 수 없습니다."),
    NOT_FOUND_TOKEN(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "토큰이 없습니다."),
    EXPIRED_TOKEN(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "만료된 토큰입니다."),
    RETRIEVE_ERROR(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "조회에 실패했습니다."),
    FILE_UPLOAD_FAIL(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패 했습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "Refresh Token이 만료되었습니다. 다시 로그인하세요.");
    private final int status;
    private final HttpStatus httpStatus;
    private final String message;

}