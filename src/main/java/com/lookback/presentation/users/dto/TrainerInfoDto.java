package com.lookback.presentation.users.dto;

import com.lookback.domain.common.constant.enums.TrainerInfoType;
import com.lookback.domain.user.entity.TrainerInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerInfoDto {

    private Long id;
    private Long trainerId; // Trainer 엔티티 ID만 가져옴
    private String trainerInfoName;
    private String trainerInfoContent;
    private TrainerInfoType trainerInfoType;
    private String trainerInfoInProgress;
    private LocalDateTime trainerInfoAcquisitionDate;
    private LocalDateTime trainerInfoStartAt;
    private LocalDateTime trainerInfoEndAt;

    public static TrainerInfoDto fromEntity(TrainerInfo entity) {
        if (entity == null) {
            return null;
        }
        return TrainerInfoDto.builder()
                .id(entity.getId())
                .trainerId(entity.getTrainer() != null ? entity.getTrainer().getId() : null)
                .trainerInfoName(entity.getTrainerInfoName())
                .trainerInfoContent(entity.getTrainerInfoContent())
                .trainerInfoType(entity.getTrainerInfoType())
                .trainerInfoInProgress(entity.getTrainerInfoInProgress())
                .trainerInfoAcquisitionDate(entity.getTrainerInfoAcquisitionDate())
                .trainerInfoStartAt(entity.getTrainerInfoStartAt())
                .trainerInfoEndAt(entity.getTrainerInfoEndAt())
                .build();
    }
}
