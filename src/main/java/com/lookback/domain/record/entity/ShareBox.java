package com.lookback.domain.record.entity;

import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;

import javax.print.DocFlavor;
import java.time.LocalDateTime;

@Entity
@Table(name = "SHARE_BOX")
public class ShareBox {

    @Id @GeneratedValue
    @Column(name = "SHARE_BOX_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPIENT_USERS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users recipientUser; //수신자

    private LocalDateTime shareDate;
    private String shareStatus;
    private String feedBack;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

}
