package com.lookback.domain.record.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.ExerciseDetailTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EXERCISE_RECORD_DETAIL")
@Getter
@Setter
public class ExerciseRecordDetail extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="EXERCISE_RECORD_DETAIL_ID")
    private Long id;

    private Long ord;
    private Integer repsPerSet;
    private Integer weight;
    private Integer duration;
    private String memo;

    @Enumerated(EnumType.STRING)
    private ExerciseDetailTypeEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_RECORD_ID",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ExerciseRecord exerciseRecord;
}
