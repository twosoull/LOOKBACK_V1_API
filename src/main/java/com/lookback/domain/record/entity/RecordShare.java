package com.lookback.domain.record.entity;

import com.lookback.domain.common.constant.enums.ShareStatus;
import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;

import javax.print.DocFlavor;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "RECORD_SHARE")
public class RecordShare {

    @Id @GeneratedValue
    @Column(name = "RECORD_SHARE_ID")
    private Long id;

    //작성자(예: 트레이너)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINING_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Training training;

    @Enumerated(EnumType.STRING) // Enum을 String으로 저장
    @Column(nullable = false)
    private ShareStatus shareStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

}
