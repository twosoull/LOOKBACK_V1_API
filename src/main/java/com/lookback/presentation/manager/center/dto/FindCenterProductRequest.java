package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.common.constant.enums.CenterProductType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindCenterProductRequest {

    private Long centerId;
    private CenterProductType centerProductType;

}
