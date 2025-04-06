package com.lookback.presentation.record.dto;

import com.lookback.domain.file.entity.UploadFile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveExerciseRecordRequest {
    private List<SaveExerciseRecordRequest> exerciseRecords;
    private Long userId;
    private Long exerciseRecordId;
    private Long recordId;
    private Long exerciseId;
    private String duration;
    private String memo;
    private Integer ord;
    private String imageUrl;
    private String exerciseName;
    private List<ExerciseRecordDetailDto> exerciseRecordDetails;
    private List<UploadFile> uploadFiles;
}
