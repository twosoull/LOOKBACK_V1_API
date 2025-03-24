package com.lookback.presentation.record.dto;

import com.lookback.common.converter.CommonConverter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SaveRecordRequest {

    private Long id;
    private Long usersId;
    private Long trainingId;
    private String recordDate;
    private String recordTimeStart;
    private String recordTimeEnd;
    private int exerciseMinute;
    private String comment;

    public LocalDate convertedRecordDate() {
        return CommonConverter.convertStringToLocalDate(recordDate);
    }

    public LocalTime convertedRecordTimeStart() {
        return CommonConverter.convertStringToLocalTime(recordTimeStart);
    }

    public LocalTime convertedRecordTimeEnd() {
        return CommonConverter.convertStringToLocalTime(recordTimeEnd);
    }
}
