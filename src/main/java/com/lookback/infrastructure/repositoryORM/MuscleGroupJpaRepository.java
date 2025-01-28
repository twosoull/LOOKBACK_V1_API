package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.muscle.entity.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleGroupJpaRepository extends JpaRepository<MuscleGroup, Long> {
    List<MuscleGroup> findByMuscleCategoryId(Long muscleCategoryId);
}
