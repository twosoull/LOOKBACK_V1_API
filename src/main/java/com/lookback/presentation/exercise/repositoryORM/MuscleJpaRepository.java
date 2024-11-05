package com.lookback.presentation.exercise.repositoryORM;

import com.lookback.domain.muscle.entity.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleJpaRepository extends JpaRepository<Muscle, Long> {
    List<Muscle> findByMuscleGroupId(Long muscleGroupId);
}
