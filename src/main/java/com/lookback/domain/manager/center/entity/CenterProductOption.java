package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.CenterProductOptionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 센터 상품 옵션
 */
@Entity
@Getter
@Setter
public class CenterProductOption extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CENTER_PRODUCT_OPTION_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CenterProductOptionType centerProductOptionType;
    private String centerProductOptionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTER_PRODUCT_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CenterProduct centerProduct;
}
