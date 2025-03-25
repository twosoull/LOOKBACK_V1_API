package com.lookback.domain.muscle.entity;

import com.lookback.common.BaseEntity;
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
public class EquipmentCategory  extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "EQUIPMENT_CATEGORY_ID")
    private Long id;
    private Long ord;
    private String name;
    private String description;
}
