package com.lookback.domain.muscle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
public class EquipmentCategory {

    @Id @GeneratedValue
    @Column(name = "EQUIPMENT_CATEGORY_ID")
    private Long id;

    private String name;

    private Long parentsId;



}
