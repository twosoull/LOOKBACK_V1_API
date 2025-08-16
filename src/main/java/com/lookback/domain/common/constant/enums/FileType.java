package com.lookback.domain.common.constant.enums;

public enum FileType {
    PROFILE("프로필 이미지"),
    EXERCISE("운동 대표 사진"),
    EXERCISE_RECORD_MEDIA("운동 기록 이미지"),
    MAIN_CENTER_IMAGE("센터 메인사진"),
    SUB_CENTER_IMAGE("센터 서브사진");

    private final String description;

    FileType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}