package com.lookback.domain.record.entity;

import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;

import javax.print.DocFlavor;
import java.time.LocalDateTime;

@Entity
@Table(name = "RECORD_SHARE")
public class RecordShare {

    @Id @GeneratedValue
    @Column(name = "RECORD_SHARE_ID")
    private Long id;

    //작성자(예: 트레이너)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINING_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Training training;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

}
