package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.exercise.entity.ExerciseVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseVideoJpaRepository extends JpaRepository<ExerciseVideo, Long> {
}
