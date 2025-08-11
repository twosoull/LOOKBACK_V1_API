package com.lookback.presentation.manager.trainer.dto;

import com.lookback.domain.user.entity.Trainer;
import com.lookback.presentation.users.dto.TrainerInfoDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveTrainerProfileRequest {

    private String trainerOneLineIntro;
    private String selfIntroduction;
    private String trainerProfileShowYn;
    private String phone; // user
    private List<TrainerInfoDto> trainerInfos;

    public Trainer toEntity() {
        Trainer trainer = new Trainer();
        trainer.setTrainerOneLineIntro(trainerOneLineIntro);
        trainer.setSelfIntroduction(selfIntroduction);
        trainer.setTrainerProfileShowYn(trainerProfileShowYn);
        return trainer;
    }

}
