package com.lookback.presentation.record.dto;

import com.lookback.domain.common.constant.enums.ShareStatus;
import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.record.entity.Record;
import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class SaveRecordResponse {

    private Long recordId;
    private Long usersId;
    private Long trainingId;
    private String recordDate;
    private String recordTimeStart;
    private String recordTimeEnd;
    private String shareStatus;

    public static SaveRecordResponse fromEntity(Record record) {
        return SaveRecordResponse.builder()
                .recordId(record.getId())
                .usersId(record.getUsers().getId())
                .trainingId(record.getTrainingIdSafe())
                .recordDate(record.getRecordDate().toString())
                .recordTimeStart(record.getRecordTimeStart().toString())
                .recordTimeEnd(record.getRecordTimeEnd().toString())
                .shareStatus(record.getShareStatus().name())
                .build();
    }
}
