package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import com.lookback.domain.manager.center.entity.CenterFacility;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
* 센터 편의시설
* */
@Getter
@Setter
@Builder
public class CenterFacilityDto {

    private Long centerFacilityId;
    private String centerFacilityName;

    public static CenterFacilityDto fromEntity(CenterFacility entity) {
        return CenterFacilityDto.builder()
                .centerFacilityName(entity.getCenterFacilityName())
                .build();
    }

}
