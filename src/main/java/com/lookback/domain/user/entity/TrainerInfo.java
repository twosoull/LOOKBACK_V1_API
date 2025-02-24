package com.lookback.domain.user.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.catalina.User;

@Entity
@Table(name = "TRAINER_INFO")
@Getter
public class TrainerInfo extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users user;

    private String selfIntroduction;
}
