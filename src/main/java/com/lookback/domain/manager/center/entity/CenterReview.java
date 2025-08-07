package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.tree.AbstractEntity;

@Entity
@Getter
@Setter
public class CenterReview extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CENTER_REVIEW_ID")
    private Long id;

    private String centerReviewContent;

    private Long centerReviewPoint;
    private String centerReviewProductName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Center center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USERS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users users;

}
