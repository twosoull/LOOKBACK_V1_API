package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CenterSnsType {

    BLOG("블로그"),
    INSTAGRAM("인스타그램"),
    YOUTUBE("유튜브"),
    HOMEPAGE("홈페이지");

    private final String message;

    CenterSnsType(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

}
