package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ShareStatus {
    SAVED("저장"),
    SHARED("공유완료"), // 저장완료 및 공유까지 완료
    TEMP("임시저장"),
    CANCEL("취소");

    private final String message;

    ShareStatus(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
