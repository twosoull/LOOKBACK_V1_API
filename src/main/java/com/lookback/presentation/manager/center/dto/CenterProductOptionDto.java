package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.common.constant.enums.CenterProductOptionType;
import lombok.Getter;
import lombok.Setter;

/**
 * 센터 상품 옵션
 */
@Getter
@Setter
public class CenterProductOptionDto {

    private Long centerProductOptionId;

    private CenterProductOptionType centerProductOptionType;
    private String centerProductOptionName;
}
