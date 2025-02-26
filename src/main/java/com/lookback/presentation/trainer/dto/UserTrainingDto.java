package com.lookback.presentation.trainer.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserTrainingDto {

    private Long userId;
    private String userName;
    private Long birthDate;
    private LocalDateTime latestCreatedAt;

    public UserTrainingDto(Long userId, String userName, Long birthDate, LocalDateTime latestCreatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.birthDate = birthDate;
        this.latestCreatedAt = latestCreatedAt;
    }
}
