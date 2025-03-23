package com.lookback.presentation.trainer.dto;

import com.lookback.domain.user.entity.Training;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CancelTrainingDto {

    private Long trainingId;
    private Long trainerId;

    private Long studentId;
    private String trainingStatus;
    private LocalDateTime updatedAt;

    public CancelTrainingDto() {

    }
    public static CancelTrainingDto fromEntity(Training training) {
        return CancelTrainingDto.builder()
                .trainingId(training.getId())
                .trainerId(training.getTrainer().getId())
                .studentId(training.getStudent().getId())
                .trainingStatus(training.getTrainingStatus().name())
                .updatedAt(training.getUpdatedAt())
                .build();
    }
}
