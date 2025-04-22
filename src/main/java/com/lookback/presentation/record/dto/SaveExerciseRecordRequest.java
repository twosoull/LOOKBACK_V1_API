package com.lookback.presentation.record.dto;

import com.lookback.domain.file.entity.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveExerciseRecordRequest {
    private List<ExerciseRecordDto> exerciseRecords;
    private Long recordId;
}
