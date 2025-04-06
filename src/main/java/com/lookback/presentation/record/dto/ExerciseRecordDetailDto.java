package com.lookback.presentation.record.dto;

import com.lookback.domain.common.constant.enums.ExerciseDetailTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRecordDetailDto {

    private Long exerciseRecordDetailId;
    private Long exerciseRecordId;
    private Long ord;
    private Integer repsPerSet;
    private Integer weight;

    private ExerciseDetailTypeEnum type;

}
