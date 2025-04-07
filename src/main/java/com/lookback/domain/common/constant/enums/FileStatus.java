package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FileStatus {
    SAVED("저장"),
    DELETE("삭제"),// 저장완료 및 공유까지 완료
    TEMP("임시저장");

    private final String message;

    FileStatus(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
