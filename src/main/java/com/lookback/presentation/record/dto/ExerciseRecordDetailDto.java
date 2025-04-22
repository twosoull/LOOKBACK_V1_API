package com.lookback.presentation.record.dto;

import com.lookback.domain.common.constant.enums.ExerciseDetailTypeEnum;
import com.lookback.domain.record.dto.ExerciseRecordDetailDomainDto;
import com.lookback.domain.record.entity.ExerciseRecordDetail;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRecordDetailDto {

    private Long exerciseRecordDetailId;
    private Long exerciseRecordId;
    private Long ord;
    private Integer repsPerSet;
    private Integer weight;
    private String type;

    public static ExerciseRecordDetailDto of(Long exerciseRecordDetailId, Long exerciseRecordId, Long ord, Integer repsPerSet, Integer weight, String type) {
        return ExerciseRecordDetailDto.builder()
                .exerciseRecordDetailId(exerciseRecordDetailId)
                .exerciseRecordId(exerciseRecordId)
                .ord(ord)
                .repsPerSet(repsPerSet)
                .weight(weight)
                .type(type)
                .build();
    }

    public static List<ExerciseRecordDetailDto> listOf(List<ExerciseRecordDetailDomainDto> exerciseRecordDetailDomainDtos) {
        return exerciseRecordDetailDomainDtos != null ?
                exerciseRecordDetailDomainDtos.stream().map(erd -> ExerciseRecordDetailDto.of(
                erd.getExerciseRecordDetailId(),
                erd.getExerciseRecordId(),
                erd.getOrd(),
                erd.getRepsPerSet(),
                erd.getWeight(),
                erd.getType().name()
        )).toList()
        :null;
    }
}
