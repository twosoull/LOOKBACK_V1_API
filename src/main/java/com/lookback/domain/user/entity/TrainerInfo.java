package com.lookback.domain.user.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.TrainerInfoType;
import com.lookback.domain.file.entity.UploadFile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TrainerInfoType 정보 유형에 따라 정보가 변경 됌
 */
@Getter
@Setter
@Entity
@Table(name = "TRAINER_INFO")
public class TrainerInfo extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Trainer trainer;

    private String trainerInfoName;                    // 정보이름 (ex.경력명)
    private String trainerInfoContent;                 // 그외 내용(필요시)

    @Enumerated(EnumType.STRING)
    private TrainerInfoType trainerInfoType;           // 정보 유형(학력, 경력, 자격증 등)

    private String trainerInfoInProgress;              // 진행여부
    private LocalDateTime trainerInfoAcquisitionDate;  // 취득일
    private LocalDateTime trainerInfoStartAt;          // 시작일 (ex.입학, 경력 시작일 등)
    private LocalDateTime trainerInfoEndAt;            // 종료일 (ex.졸업, 경력 종료일 등)

}
