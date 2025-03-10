package com.lookback.infrastructure.queryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTrainingQueryDto {
    private Long id;
    private String userName;
    private Long birthDt;
    private LocalDate latestCreatedAt;
}
