package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.common.constant.enums.CenterProductOptionType;
import com.lookback.domain.manager.center.entity.CenterProduct;
import com.lookback.domain.manager.center.entity.CenterProductOption;
import lombok.*;

/**
 * 센터 상품 옵션
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterProductOptionDto {

    private Long centerProductOptionId;

    private CenterProductOptionType centerProductOptionType;

    private String centerProductOptionName;

    public static CenterProductOptionDto fromEntity(CenterProductOption centerProductOption) {
        return CenterProductOptionDto.builder()
                .centerProductOptionType(centerProductOption.getCenterProductOptionType())
                .centerProductOptionName(centerProductOption.getCenterProductOptionName())
                .build();
    }

    public CenterProductOption toEntity(CenterProduct centerProduct) {
        CenterProductOption centerProductOption = new CenterProductOption();
        centerProductOption.setCenterProductOptionType(centerProductOptionType);
        centerProductOption.setCenterProductOptionName(centerProductOptionName);
        centerProductOption.setCenterProduct(centerProduct);
        return centerProductOption;
    }
}
