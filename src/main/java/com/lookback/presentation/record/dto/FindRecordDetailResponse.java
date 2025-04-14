package com.lookback.presentation.record.dto;

import com.lookback.domain.common.constant.enums.ShareStatus;
import com.lookback.domain.record.dto.ExerciseRecordDetailDomainDto;
import com.lookback.domain.record.dto.ExerciseRecordDomainDto;
import com.lookback.domain.record.dto.RecordWithDetailsDto;
import com.lookback.presentation.users.dto.UsersDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindRecordDetailResponse {
    private Long recordId;
    private Long trainingId;
    private LocalDate recordDate;
    private LocalTime recordTimeStart;
    private LocalTime recordTimeEnd;
    private int exerciseMinute;
    private String comment;
    private ShareStatus shareStatus;

    private UsersDto trainer;
    private UsersDto member;
    private List<ExerciseRecordDto> exerciseRecords;
    private List<ExerciseRecordDetailDto> exerciseRecordDetails;

    public FindRecordDetailResponse(Long recordId, Long trainingId, LocalDate recordDate, LocalTime recordTimeStart, LocalTime recordTimeEnd, int exerciseMinute, String comment, ShareStatus shareStatus, UsersDto member) {
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

    public static FindRecordDetailResponse fromDomain(RecordWithDetailsDto recordWithDetailsDto
                                                      ) {
        return FindRecordDetailResponse.builder()
                .recordId(recordWithDetailsDto.getRecordId())
                .trainingId(recordWithDetailsDto.getTrainingId())
                .recordDate(recordWithDetailsDto.getRecordDate())
                .recordTimeStart(recordWithDetailsDto.getRecordTimeStart())
                .recordTimeEnd(recordWithDetailsDto.getRecordTimeEnd())
                .exerciseMinute(recordWithDetailsDto.getExerciseMinute())
                .comment(recordWithDetailsDto.getComment())
                .shareStatus(recordWithDetailsDto.getShareStatus())
                .trainer(UsersDto.of(recordWithDetailsDto.getTrainer().getUserName(), recordWithDetailsDto.getTrainer().getNickName()))
                .member(UsersDto.fromDomainDto(recordWithDetailsDto.getMember()))
                .exerciseRecords(ExerciseRecordDto.listOf(recordWithDetailsDto.getExerciseRecords()))
                .exerciseRecordDetails(ExerciseRecordDetailDto.listOf(recordWithDetailsDto.getExerciseRecordDetails()))
                .build();
    }
}
