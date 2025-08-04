package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CenterSnsType {

    BLOG("BLOG"),
    INSTAGRAM("INSTAGRAM"),
    YOUTUBE("YOUTUBE"),
    HOMEPAGE("HOMEPAGE");

    private final String message;

    CenterSnsType(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

}
