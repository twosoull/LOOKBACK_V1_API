package com.lookback.presentation.record.dto;

import com.lookback.domain.file.entity.UploadFile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveExerciseRecordRequest {
    private String exerciseRecordId;
    private String recordId;
    private String exerciseId;
    private String duration;
    private String memo;
    private Long   ord;
    private String imageUrl;
    private String exerciseName;
    private List<ExerciseRecordDetailDto> ExerciseRecordDetails;
    private List<UploadFile> uploadFiles;
}
