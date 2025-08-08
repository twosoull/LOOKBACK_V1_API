package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum CenterTrainerStatus {

    REQUESTED("REQUESTED", "승인요청"),
    APPROVED("APPROVED", "승인"),
    LEFT("LEFT", "나감");

    @Getter
    private final String code;
    private final String message;

    CenterTrainerStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
