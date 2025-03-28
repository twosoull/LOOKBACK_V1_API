package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.exercise.repository.EquipmentRepository;
import com.lookback.domain.muscle.entity.Equipment;
import com.lookback.infrastructure.repositoryORM.EquipmentJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class EquipmentRepositoryImpl implements EquipmentRepository {

    private final EquipmentJpaRepository equipmentJpaRepository;

    @Override
    public List<Equipment> findAll() {
        return equipmentJpaRepository.findAll();
    }
}
