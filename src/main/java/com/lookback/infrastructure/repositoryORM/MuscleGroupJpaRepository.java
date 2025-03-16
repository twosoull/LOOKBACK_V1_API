package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.infrastructure.repositoryCustom.MuscleGroupRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleGroupJpaRepository extends JpaRepository<MuscleGroup, Long>, MuscleGroupRepositoryCustom {
    List<MuscleGroup> findByMuscleCategoryId(Long muscleCategoryId);
}
