package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CenterProductDto extends BaseEntity {

    private Long id;

    private String centerProductName;               // 센터_상품_이름
    private String centerProductUsagePeriod;        // 센터_상품_이용기간
    private String centerProductPricingBasisType;   // 센터_상품_가격기준_타입
    private String centerProductOriPrice;           // 센터_상품_정가
    private String centerProductDiscountPrice;      // 센터_상품_할인가
    private String centerProductDiscountRate;       // 센터_상품_할인율
    private String centerProductNotice;             // 센터_상품_안내사항
    private String centerProductUseYn;              // 센터_상품_할인_사용여부
    private String centerProductType;               // 센터_상품_구분_유형

    private List<CenterProductOptionDto> centerProductOptions = new ArrayList<>(); // 센터_상품_옵션

}
