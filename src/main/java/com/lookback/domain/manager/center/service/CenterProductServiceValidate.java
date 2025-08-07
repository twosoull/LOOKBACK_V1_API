package com.lookback.domain.manager.center.service;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.presentation.manager.center.dto.FindCenterProductDetailRequest;
import com.lookback.presentation.manager.center.dto.FindCenterProductRequest;
import com.lookback.presentation.manager.center.dto.RemoveCenterProductRequest;
import com.lookback.presentation.manager.center.dto.SaveCenterProductRequest;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

public class CenterProductServiceValidate {

    public static void saveCenterProduct(SaveCenterProductRequest saveCenterProductRequest) {
        if (saveCenterProductRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }
    }

    public static void findCenterProducts(FindCenterProductRequest findCenterProductRequest) {
        if (findCenterProductRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }
    }

    public static void findCenterProductDetail(FindCenterProductDetailRequest findCenterProductDetailRequest) {
        if (findCenterProductDetailRequest.getCenterProductId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }
    }

    public static void removeCenterProduct(RemoveCenterProductRequest removeCenterProductRequest) {
        if (removeCenterProductRequest.getCenterProductId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }
    }
}
