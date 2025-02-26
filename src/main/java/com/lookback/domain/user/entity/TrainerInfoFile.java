package com.lookback.domain.user.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "TRAINER_INFO_FILE")
@Getter
public class TrainerInfoFile extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINER_INFO_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private TrainerInfo trainerInfoDetail;

    private String saveFileName;
    private String originalFileName;
    private String filePath;
    private String fileExtension;
    private String fileSize;
    private String deleted;

}
