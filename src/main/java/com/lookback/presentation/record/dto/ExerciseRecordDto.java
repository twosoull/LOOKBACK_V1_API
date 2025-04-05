package com.lookback.presentation.record.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExerciseRecordDto {

    private Long exerciseRecordId;
    private String exerciseName;
    private Integer sets;
    private Integer weight;
    private Integer ord;

    private List<ExerciseRecordMediaDto> exerciseRecordMediaDtos = new ArrayList<>();

}
