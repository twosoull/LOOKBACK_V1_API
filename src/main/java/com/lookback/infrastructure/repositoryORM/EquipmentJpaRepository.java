package com.lookback.infrastructure.repositoryORM;


import com.lookback.domain.muscle.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentJpaRepository extends JpaRepository<Equipment, Long> {
}
