package com.lookback.domain.manager.center.repository;

import com.lookback.domain.manager.center.entity.Center;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository {

    Center findById(Long id);

    void save(Center center);
}
