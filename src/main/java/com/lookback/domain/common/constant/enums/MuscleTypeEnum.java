package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MuscleTypeEnum {
    AGONIST("주동근"),     // 주동근
    SYNERGIST("보조근");   // 보조근

    private final String message;

    MuscleTypeEnum(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

}
