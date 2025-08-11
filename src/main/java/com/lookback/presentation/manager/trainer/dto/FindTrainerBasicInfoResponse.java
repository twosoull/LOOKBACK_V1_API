package com.lookback.presentation.manager.trainer.dto;

import com.lookback.common.converter.CommonConverter;
import com.lookback.domain.user.entity.Trainer;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindTrainerBasicInfoResponse {

    private String trainerName;
    private String phone;
    private String email;
    private String birthDt;
    private String gender;

    public static FindTrainerBasicInfoResponse fromEntity(Trainer trainer) {
        return FindTrainerBasicInfoResponse.builder()
                .trainerName(trainer.getUser().getUserName())
                .phone(trainer.getUser().getPhone())
                .email(trainer.getUser().getEmail())
                .birthDt(CommonConverter.formatDataOfDot(trainer.getUser().getBirthDt()))
                .gender(trainer.getUser().getGender())
                .build();
    }
}
