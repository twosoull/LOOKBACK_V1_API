package com.lookback.presentation.trainer.dto;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberDto {

    private Long trainerId;
    private String trainerName;
    private String trainerNickName;
    private String trainingStatus;
    private String studentName;
    private String studentNickName;
    private boolean hasOtherTraining;
    private boolean alreadyTraining; // 요청한 트레이너와 연결되어있었는지 확인하는 변수
}
