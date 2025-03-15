package com.lookback.presentation.trainer.dto;

import com.lookback.common.converter.CommonConverter;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserTrainingDto {

    private Long userId;
    private String userName;
    private Long age;
    private String latestCreatedAt;
    private List<UserTrainingDto> list = new ArrayList<>();
    private int totalCount;

    public UserTrainingDto() {
    }

    public UserTrainingDto(Long userId, String userName, Long birthDate, LocalDate latestCreatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.age = CommonConverter.ageConverter(birthDate);
        this.latestCreatedAt = CommonConverter.formatLocalDateTime(latestCreatedAt);
    }

    public static UserTrainingDto fromList(List<UserTrainingDto> list, int totalCount) {
        UserTrainingDto userTrainingDto = new UserTrainingDto();
        userTrainingDto.setList(list);
        userTrainingDto.setTotalCount(totalCount);
        return userTrainingDto;
    }
}
