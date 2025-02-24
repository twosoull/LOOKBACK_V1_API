package com.lookback.domain.user.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "TRAINER_INFO_DETAIL_FILE")
@Getter
public class TrainerInfoDetailFile extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINER_INFO_DETAIL_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private TrainerInfoDetail trainerInfoDetail;

    private String saveFileName;
    private String originalFileName;
    private String filePath;
    private String fileExtension;
    private String fileSize;
    private String deleted;

}
