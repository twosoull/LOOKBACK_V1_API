package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
public class CenterOperateTime extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CENTER_OPERATE_TIME_ID")
    private Long id;

    private String centerOperateDay;
    private LocalTime centerOperateStartTime;
    private LocalTime centerOperateEndTime;
    private String centerOperateUseYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Center center;

}
