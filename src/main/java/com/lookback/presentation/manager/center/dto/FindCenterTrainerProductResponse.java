package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.manager.center.entity.CenterProduct;
import com.lookback.domain.manager.center.entity.CenterTrainer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCenterTrainerProductResponse {

    private Long centerId;
    private List<CenterProductDto> centerTrainerProducts;
    private int page;
    private int totalCount;

    public static FindCenterTrainerProductResponse fromEntity(Long centerId, List<CenterProduct> centerProducts, int page, int totalCount) {
        return FindCenterTrainerProductResponse.builder()
                .centerId(centerId)
                .centerTrainerProducts(centerProducts.stream().map(
                        centerProduct -> CenterProductDto.fromEntity(centerProduct)
                ).toList())
                .page(page)
                .totalCount(totalCount)
                .build();
    }

}
