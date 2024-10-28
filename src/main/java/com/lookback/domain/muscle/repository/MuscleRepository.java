package com.lookback.domain.muscle.repository;

import com.lookback.domain.muscle.entity.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Long> {
    List<Muscle> findByMuscleGroupId(Long muscleGroupId);
}
