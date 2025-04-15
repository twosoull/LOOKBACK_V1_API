package com.lookback.domain.record.dto;

import com.lookback.domain.common.constant.enums.ExerciseTypeEnum;
import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.record.entity.ExerciseRecordDetail;
import com.lookback.domain.record.entity.Record;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ExerciseRecordDomainDto {

    private Long exerciseRecordId;
    private String exerciseName;
    private ExerciseTypeEnum exerciseType;
    private Long exerciseId;
    private String memo;
    private Integer ord;
    private String agonistMuscleName;
    private String synergistMuscleName;
    private List<UploadFileDomainDto> UploadFileDomainDto = new ArrayList<>();
    private List<ExerciseRecordDetailDomainDto> exerciseRecordDetails;

    public ExerciseRecordDomainDto(Long exerciseRecordId,String exerciseName, ExerciseTypeEnum exerciseType, Long exerciseId, String memo, Integer ord) {
        this.exerciseRecordId = exerciseRecordId;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.exerciseId = exerciseId;
        this.memo = memo;
        this.ord = ord;
    }

}
