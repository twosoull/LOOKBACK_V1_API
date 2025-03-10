package com.lookback.presentation.trainer.dto;

import com.lookback.common.converter.CommonConverter;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserTrainingDto {

    private Long userId;
    private String userName;
    private Long age;
    private String latestCreatedAt;

    public UserTrainingDto(Long userId, String userName, Long birthDate, LocalDate latestCreatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.age = CommonConverter.ageConverter(birthDate);
        this.latestCreatedAt = CommonConverter.formatLocalDateTime(latestCreatedAt);
    }
}
