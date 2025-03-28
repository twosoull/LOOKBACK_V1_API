package com.lookback.domain.exercise.repository;

import com.lookback.domain.muscle.entity.Equipment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository {
    List<Equipment> findAll();
}
