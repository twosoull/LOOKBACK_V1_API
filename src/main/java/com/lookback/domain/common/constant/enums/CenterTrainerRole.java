package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CenterTrainerRole {

    OWNER("소유자"),                // 소유자 (센터의 실질적 소유자)
    SUPER_ADMIN("최고관리자"),       // 최고관리자 (센터 전체를 관리하는 관리자)
    GENERAL("일반사용자");            // 일반 사용자 (기본 권한)

    private final String message;

    CenterTrainerRole(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }


}
