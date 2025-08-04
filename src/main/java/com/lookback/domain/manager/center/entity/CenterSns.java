package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.CenterSnsType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 센터 SNS
 */
@Entity
@Getter
@Setter
public class CenterSns extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CENTER_SNS_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CenterSnsType centerSnsType;
    private String centerSnsName;
    private String centerSnsUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Center center;
}
