package com.lookback.domain.record.dto;

import com.lookback.domain.common.constant.enums.ShareStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RecordWithDetailsDto {
    private Long recordId;
    private Long trainingId;
    private LocalDate recordDate;
    private LocalTime recordTimeStart;
    private LocalTime recordTimeEnd;
    private int exerciseMinute;
    private String comment;
    private ShareStatus shareStatus;

    private UsersDomainDto trainer;
    private UsersDomainDto member;
    private List<ExerciseRecordDomainDto> exerciseRecords;
    private List<ExerciseRecordDetailDomainDto> exerciseRecordDetails;

    public RecordWithDetailsDto(Long recordId, Long trainingId, LocalDate recordDate, LocalTime recordTimeStart, LocalTime recordTimeEnd, int exerciseMinute, String comment, ShareStatus shareStatus, UsersDomainDto member) {
        this.recordId = recordId;
        this.trainingId = trainingId;
        this.recordDate = recordDate;
        this.recordTimeStart = recordTimeStart;
        this.recordTimeEnd = recordTimeEnd;
        this.exerciseMinute = exerciseMinute;
        this.comment = comment;
        this.shareStatus = shareStatus;
        this.member = member;
    }
}
