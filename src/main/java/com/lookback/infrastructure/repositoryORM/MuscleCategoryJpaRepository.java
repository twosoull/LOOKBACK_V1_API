package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.muscle.entity.MuscleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MuscleCategoryJpaRepository extends JpaRepository<MuscleCategory, Long> {
    Optional<MuscleCategory> findByMuscleCategoryName(String muscleCategoryName);

    List<MuscleCategory> findByParentIsNotNull();
}
