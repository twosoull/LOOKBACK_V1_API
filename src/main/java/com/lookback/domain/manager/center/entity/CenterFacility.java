package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

/*
* 센터 편의시설
* */
@Entity
@Getter
public class CenterFacility extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CENTER_FACILITY_ID")
    private Long id;

    private String centerFacilityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Center center;

}
