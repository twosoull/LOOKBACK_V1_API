package com.lookback.presentation.record.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRecordMediaDto {

    private Long exerciseRecordMediaId;
    private String fileName;
    private String path;
    private String orgFileName;
    private String fileType;
}
