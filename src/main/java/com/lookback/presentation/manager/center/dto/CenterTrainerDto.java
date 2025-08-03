package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.CenterTrainerRole;
import com.lookback.domain.user.entity.Trainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CenterTrainerDto {

    private Long centerTrainerId;
    private CenterTrainerRole centerTrainerRole;
    List<CenterProductDto> centerProducts = new ArrayList<>();

}
