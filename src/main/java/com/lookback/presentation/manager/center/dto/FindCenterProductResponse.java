package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterProduct;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCenterProductResponse {

    private Long centerId;
    private List<CenterProductDto> centerProducts = new ArrayList<>();
    private int page;
    private Long totalCount;

    public static FindCenterProductResponse fromEntity(Long centerId, List<CenterProduct> centerProducts, int page, Long totalCount) {
        return FindCenterProductResponse.builder()
                .centerId(centerId)
                .centerProducts(centerProducts.stream().map(
                        centerProduct -> CenterProductDto.fromEntity(centerProduct)
                ).toList())
                .page(page)
                .totalCount(totalCount)
                .build();

    }
}
