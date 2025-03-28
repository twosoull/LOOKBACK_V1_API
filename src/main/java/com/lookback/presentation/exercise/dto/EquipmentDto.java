package com.lookback.presentation.exercise.dto;

import com.lookback.domain.muscle.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EquipmentDto {

    private Long EquipmentCategoryId;
    private Long ord;
    private String name;
    private String description;

    public static EquipmentDto fromEntity(Equipment equipment) {
        return EquipmentDto.builder()
                .EquipmentCategoryId(equipment.getId())
                .ord(equipment.getOrd())
                .name(equipment.getName())
                .description(equipment.getDescription())
                .build();
    }
}
