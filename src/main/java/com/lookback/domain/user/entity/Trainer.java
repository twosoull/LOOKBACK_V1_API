package com.lookback.domain.user.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.catalina.User;

import java.util.List;

@Entity
@Table(name = "TRAINER")
@Getter
public class Trainer extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "TRAINER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users user;

    @OneToMany(mappedBy = "trainer")
    private List<Training> training;

    private String selfIntroduction;
}
