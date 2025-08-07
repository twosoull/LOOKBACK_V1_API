package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.common.constant.enums.CenterProductType;
import com.lookback.domain.manager.center.entity.CenterProduct;
import com.lookback.domain.manager.center.entity.CenterProductOption;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveCenterProductRequest {

    private Long centerId;
    private Long productId;
    private String centerProductName;               // 센터_상품_이름
    private String centerProductUsagePeriod;        // 센터_상품_이용기간
    private String centerProductPricingBasisType;   // 센터_상품_가격기준_타입
    private String centerProductOriPrice;           // 센터_상품_정가
    private String centerProductDiscountPrice;      // 센터_상품_할인가
    private String centerProductDiscountRate;       // 센터_상품_할인율
    private String centerProductNotice;             // 센터_상품_안내사항
    private String centerProductUseYn;              // 센터_상품_할인_사용여부

    private CenterProductType centerProductType;
    private List<CenterProductOptionDto> centerProductOptions = new ArrayList<>(); // 센터_상품_옵션

    public CenterProduct toEntity() {
        CenterProduct centerProduct = new CenterProduct();
        centerProduct.setCenterProductName(centerProductName);
        centerProduct.setCenterProductUsagePeriod(centerProductUsagePeriod);
        centerProduct.setCenterProductOriPrice(centerProductOriPrice);
        centerProduct.setCenterProductDiscountPrice(centerProductDiscountPrice);
        centerProduct.setCenterProductDiscountRate(centerProductDiscountRate);
        centerProduct.setCenterProductNotice(centerProductNotice);
        centerProduct.setCenterProductUseYn(centerProductUseYn);
        centerProduct.setCenterProductType(centerProductType);
        return centerProduct;
    }

}
