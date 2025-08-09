package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.CenterTrainerRole;
import com.lookback.domain.common.constant.enums.CenterTrainerStatus;
import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.user.entity.Trainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CenterTrainer extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CENTER_TRAINER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CenterTrainerRole centerTrainerRole;

    @Enumerated(EnumType.STRING)
    private CenterTrainerStatus centerTrainerStatus; //승인 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Center center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TRAINER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Trainer trainer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CenterProduct> centerProducts = new ArrayList<>();



    public void updateStatus(CenterTrainerStatus centerTrainerStatus) {
        this.centerTrainerStatus = centerTrainerStatus;
    }

    public void updateStatusExclusion() {
        this.centerTrainerStatus = CenterTrainerStatus.EXCLUSION;
    }

    public void updateRoleSuperAdmin() {
        this.centerTrainerRole = CenterTrainerRole.SUPER_ADMIN;
    }

    public void updateRoleGeneral() {
        this.centerTrainerRole = CenterTrainerRole.GENERAL;
    }
}
