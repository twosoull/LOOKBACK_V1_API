package com.lookback.domain.manager.center.service;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterProduct;
import com.lookback.domain.manager.center.entity.CenterProductOption;
import com.lookback.domain.manager.center.repository.CenterProductRepository;
import com.lookback.domain.manager.center.repository.CenterRepository;
import com.lookback.presentation.manager.center.dto.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.FAIL_REMOVE_PRODUCT;
import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

@Service
@RequiredArgsConstructor
public class CenterProductService {

    private final CenterRepository centerRepository;
    private final CenterProductRepository centerProductRepository;

    public void saveCenterProduct(SaveCenterProductRequest saveCenterProductRequest) {
        CenterProductServiceValidate.saveCenterProduct(saveCenterProductRequest);
        Center findCenter = centerRepository.findById(saveCenterProductRequest.getCenterId());
        if (findCenter == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        boolean isCreate = saveCenterProductRequest.getProductId() == null;
        boolean isUpdate = !isCreate;
        if (isCreate) {
            CenterProduct centerProduct = saveCenterProductRequest.toEntity();
            List<CenterProductOption> centerProductOptions = saveCenterProductRequest.getCenterProductOptions().stream().map(centerProductOptionDto -> centerProductOptionDto.toEntity(centerProduct)).toList();
            centerProduct.setCenterProductOptions(centerProductOptions);
            centerProduct.setCenter(findCenter);

            centerProductRepository.save(centerProduct);
        }

        if (isUpdate) {
            CenterProduct centerProduct = centerProductRepository.findById(saveCenterProductRequest.getProductId());
            centerProduct.update(saveCenterProductRequest);
        }

    }

    public FindCenterProductResponse findCenterProducts(FindCenterProductRequest findCenterProductRequest, Pageable pageable) {
        CenterProductServiceValidate.findCenterProducts(findCenterProductRequest);

        Page<CenterProduct> findCenterProducts;
        if (findCenterProductRequest.getCenterProductType() != null) {
           findCenterProducts = centerProductRepository
                    .findByCenterIdAndCenterProductType(findCenterProductRequest.getCenterId()
                            ,findCenterProductRequest.getCenterProductType()
                            ,pageable);
        } else {
            findCenterProducts = centerProductRepository
                    .findByCenterId(findCenterProductRequest.getCenterId()
                            ,pageable);
        }

        if (findCenterProducts.isEmpty()) {
            return FindCenterProductResponse.builder()
                    .centerId(findCenterProductRequest.getCenterId())
                    .build();
        }

        List<CenterProduct> centerProducts = findCenterProducts.getContent();
        long totalCount = findCenterProducts.getTotalElements();
        int page = findCenterProducts.getNumber();

        return FindCenterProductResponse.fromEntity(findCenterProductRequest.getCenterId(),
                centerProducts, page, totalCount
                );
    }

    public FindCenterProductDetailResponse findCenterProductDetail(FindCenterProductDetailRequest findCenterProductDetailRequest) {
        CenterProductServiceValidate.findCenterProductDetail(findCenterProductDetailRequest);
        CenterProduct fincCenterProduct = centerProductRepository.findById(findCenterProductDetailRequest.getCenterProductId());

        return FindCenterProductDetailResponse.fromEntity(fincCenterProduct);
    }

    public void removeCenterProduct(RemoveCenterProductRequest removeCenterProductRequest) {
        CenterProductServiceValidate.removeCenterProduct(removeCenterProductRequest);

        try {
            centerProductRepository.deleteById(removeCenterProductRequest.getCenterProductId());
        } catch (Exception e) {
            throw new RestApiException(FAIL_REMOVE_PRODUCT);
        }

    }
}
