package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


public enum CenterTrainerRole {

    OWNER("OWNER"),                // 센터의 실질적 소유자
    SUPER_ADMIN("SUPER_ADMIN"), // 센터 전체 관리
    GENERAL("GENERAL");        // 기본 권한

    @Getter
    private final String code;

    CenterTrainerRole(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }


}
