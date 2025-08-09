package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.common.constant.enums.CenterProductType;
import com.lookback.domain.manager.center.entity.CenterProduct;
import com.lookback.domain.manager.center.repository.CenterProductRepository;
import com.lookback.infrastructure.repositoryORM.CenterProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CenterProductRepositoryImpl implements CenterProductRepository {

    private final CenterProductJpaRepository centerProductJpaRepository;

    @Override
    public void save(CenterProduct centerProduct) {
        centerProductJpaRepository.save(centerProduct);
    }

    @Override
    public CenterProduct findById(Long productId) {
        return centerProductJpaRepository.findById(productId).orElse(null);
    }

    @Override
    public Page<CenterProduct> findByCenterIdAndCenterProductType(Long centerId, CenterProductType centerProductType, Pageable pageable) {
        return centerProductJpaRepository.findByCenterIdAndCenterProductType(centerId, centerProductType, pageable);
    }

    @Override
    public Page<CenterProduct> findByCenterId(Long centerId, Pageable pageable) {
        return centerProductJpaRepository.findByCenterId(centerId, pageable);
    }

    @Override
    public void deleteById(Long centerProductId) {
        centerProductJpaRepository.deleteById(centerProductId);
    }

    @Override
    public Page<CenterProduct> findByCenterTrainerIdAndCenterId(Long centerTrainerId, Long centerId, Pageable pageable) {
        return centerProductJpaRepository.findByCenterTrainerIdAndCenterId(centerTrainerId, centerId, pageable);
    }

}
