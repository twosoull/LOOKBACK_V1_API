package com.lookback.domain.muscle.repository;

import com.lookback.domain.muscle.entity.MuscleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MuscleCategoryRepository extends JpaRepository<MuscleCategory, Long> {
    Optional<MuscleCategory> findByMuscleCategoryName(String muscleCategoryName);
}
