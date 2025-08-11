package com.lookback.presentation.manager.trainer.dto;

import com.lookback.domain.user.entity.Trainer;
import com.lookback.domain.user.entity.TrainerInfo;
import com.lookback.presentation.users.dto.TrainerInfoDto;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindTrainerDetailResponse {

    private List<TrainerInfoDto> trainerInfos;

    private String trainerOneLineIntro;
    private String selfIntroduction;
    private String trainerProfileShowYn;
    private String phone;

    public static FindTrainerDetailResponse fromEntity(Trainer trainer) {
        return FindTrainerDetailResponse.builder()
                .trainerOneLineIntro(trainer.getTrainerOneLineIntro())
                .selfIntroduction(trainer.getSelfIntroduction())
                .trainerProfileShowYn(trainer.getTrainerProfileShowYn())
                .phone(trainer.getUser().getPhone())
                .trainerInfos(trainer.getTrainerInfos().stream().map(
                        infos -> TrainerInfoDto.fromEntity(infos)
                ).toList())
                .build();
    }
}
