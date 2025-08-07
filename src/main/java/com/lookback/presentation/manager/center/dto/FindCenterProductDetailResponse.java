package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import com.lookback.common.converter.CommonConverter;
import com.lookback.domain.common.constant.enums.CenterProductType;
import com.lookback.domain.manager.center.entity.CenterProduct;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCenterProductDetailResponse extends BaseEntity {

    private Long centerProductId;

    private String centerProductName;               // 센터_상품_이름
    private String centerProductUsagePeriod;        // 센터_상품_이용기간
    private String centerProductPricingBasisType;   // 센터_상품_가격기준_타입
    private String centerProductOriPrice;           // 센터_상품_정가
    private String centerProductDiscountPrice;      // 센터_상품_할인가
    private String centerProductDiscountRate;       // 센터_상품_할인율
    private String centerProductNotice;             // 센터_상품_안내사항
    private String centerProductUseYn;              // 센터_상품_할인_사용여부
    private CenterProductType centerProductType;               // 센터_상품_구분_유형

    private List<CenterProductOptionDto> centerProductOptions = new ArrayList<>(); // 센터_상품_옵션

    private String updateAt;

    public static FindCenterProductDetailResponse fromEntity(CenterProduct centerProduct) {
        return FindCenterProductDetailResponse.builder()
                .centerProductId(centerProduct.getId())
                .centerProductName(centerProduct.getCenterProductName())
                .centerProductUsagePeriod(centerProduct.getCenterProductUsagePeriod())
                .centerProductPricingBasisType(centerProduct.getCenterProductPricingBasisType())
                .centerProductOriPrice(centerProduct.getCenterProductOriPrice())
                .centerProductDiscountPrice(centerProduct.getCenterProductDiscountPrice())
                .centerProductDiscountRate(centerProduct.getCenterProductDiscountRate())
                .centerProductNotice(centerProduct.getCenterProductNotice())
                .centerProductUseYn(centerProduct.getCenterProductUseYn())
                .centerProductType(centerProduct.getCenterProductType())
                .centerProductOptions(centerProduct.getCenterProductOptions().stream().map(
                        centerProductOption -> CenterProductOptionDto.fromEntity(centerProductOption)
                ).toList())
                .updateAt(CommonConverter.formatLocalDateTime(centerProduct.getUpdatedAt()))
                .build();
    }
}
