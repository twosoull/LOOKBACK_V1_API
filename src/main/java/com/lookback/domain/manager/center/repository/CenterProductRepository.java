package com.lookback.domain.manager.center.repository;

import com.lookback.domain.common.constant.enums.CenterProductType;
import com.lookback.domain.manager.center.entity.CenterProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterProductRepository {
    void save(CenterProduct centerProduct);

    CenterProduct findById(Long productId);

    Page<CenterProduct> findByCenterIdAndCenterProductType(Long centerId, CenterProductType centerProductType, Pageable pageable);

    Page<CenterProduct> findByCenterId(Long centerId, Pageable pageable);

    void deleteById(Long centerProductId);

    Page<CenterProduct> findByCenterTrainerIdAndCenterId(Long centerTrainerId, Long centerId1, Pageable pageable);

}
