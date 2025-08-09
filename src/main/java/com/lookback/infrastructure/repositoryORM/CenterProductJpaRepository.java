package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.common.constant.enums.CenterProductType;
import com.lookback.domain.manager.center.entity.CenterProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterProductJpaRepository extends JpaRepository<CenterProduct, Long> {
    Page<CenterProduct> findByCenterIdAndCenterProductType(Long centerId, CenterProductType centerProductType, Pageable pageable);

    Page<CenterProduct> findByCenterId(Long centerId, Pageable pageable);

    Page<CenterProduct> findByCenterTrainerIdAndCenterId(Long centerTrainerId, Long centerId, Pageable pageable);
}
