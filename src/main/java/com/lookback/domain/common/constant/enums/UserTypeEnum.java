package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserTypeEnum {
    TRAINER("트레이너"),
    MEMBER("일반회원");

    private final String message;

    UserTypeEnum(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
