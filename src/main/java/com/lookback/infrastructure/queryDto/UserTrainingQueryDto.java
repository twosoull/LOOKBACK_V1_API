package com.lookback.infrastructure.queryDto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserTrainingQueryDto {
    private Long id;
    private String userName;
    private Long birthDt;
    private LocalDateTime latestCreatedAt;

    public UserTrainingQueryDto(Long id, String userName, Long birthDt, LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.birthDt = birthDt;
        this.latestCreatedAt = createdAt;
    }

}
