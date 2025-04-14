package com.lookback.domain.record.dto;

import com.lookback.domain.common.constant.enums.ExerciseDetailTypeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRecordDetailDomainDto {

    private Long exerciseRecordDetailId;
    private Long exerciseRecordId;
    private Long ord;
    private Integer repsPerSet;
    private Integer weight;
    private Integer duration;
    private String memo;

    private ExerciseDetailTypeEnum type;

    public ExerciseRecordDetailDomainDto(Long exerciseRecordDetailId,Long exerciseRecordId, Long ord, Integer repsPerSet, Integer weight, Integer duration, String memo, ExerciseDetailTypeEnum type) {
        this.exerciseRecordDetailId = exerciseRecordDetailId;
        this.exerciseRecordId = exerciseRecordId;
        this.ord = ord;
        this.repsPerSet = repsPerSet;
        this.weight = weight;
        this.duration = duration;
        this.memo = memo;
        this.type = type;
    }
}
