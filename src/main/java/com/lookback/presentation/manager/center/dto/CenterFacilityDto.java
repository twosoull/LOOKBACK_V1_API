package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterFacility;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

/*
* 센터 편의시설
* */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CenterFacilityDto {

    private Long centerFacilityId;
    private String centerFacilityName;

    public CenterFacilityDto(String centerFacilityName) {
        this.centerFacilityName = centerFacilityName;
    }

    public static CenterFacilityDto fromEntity(CenterFacility entity) {
        return CenterFacilityDto.builder()
                .centerFacilityName(entity.getCenterFacilityName())
                .build();
    }

    public CenterFacility toEntity(Center center) {
        CenterFacility entity = new CenterFacility();
        entity.setCenterFacilityName(this.centerFacilityName);
        entity.setCenter(center); // 연관관계
        return entity;
    }

}
