package com.lookback.domain.muscle.repository;

import com.lookback.domain.muscle.entity.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
    List<MuscleGroup> findByMuscleCategoryId(Long muscleCategoryId);
}
