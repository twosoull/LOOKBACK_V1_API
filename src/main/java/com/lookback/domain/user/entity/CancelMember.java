package com.lookback.domain.user.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "CANCEL_MEMBER")
@Getter
public class CancelMember extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String reason;
    private String reasonDetail;
}
