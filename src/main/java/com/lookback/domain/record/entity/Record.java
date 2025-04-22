package com.lookback.domain.record.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.ShareStatus;
import com.lookback.domain.record.command.RecordCommand;
import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import com.lookback.presentation.record.dto.SaveRecordRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@Table(name = "RECORD")
public class Record extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "RECORD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "USERS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TRAINING_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Training training;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ExerciseRecord> exerciseRecords = new ArrayList<>();

    private LocalDate recordDate;
    private LocalTime recordTimeStart;
    private LocalTime recordTimeEnd;
    private int exerciseMinute;
    private String comment;

    @Enumerated(EnumType.STRING)
    private ShareStatus shareStatus;

    public Record() {}

    public Long getTrainingIdSafe() {
        return training != null ? training.getId() : null;
    }

    public static Record createFromSaveDto(Users users, Training training, SaveRecordRequest saveRecordRequest) {
        return Record.builder()
                .users(users)
                .training(training)
                .recordDate(saveRecordRequest.convertedRecordDate())
                .recordTimeStart(saveRecordRequest.convertedRecordTimeStart())
                .recordTimeEnd(saveRecordRequest.convertedRecordTimeEnd())
                .shareStatus(ShareStatus.TEMP)
                .build();
    }

    public static Record createFromSaveDto(Users users, SaveRecordRequest saveRecordRequest) {
        return Record.builder()
                .users(users)
                .recordDate(saveRecordRequest.convertedRecordDate())
                .recordTimeStart(saveRecordRequest.convertedRecordTimeStart())
                .recordTimeEnd(saveRecordRequest.convertedRecordTimeEnd())
                .shareStatus(ShareStatus.TEMP)
                .build();
    }
    /*
    public static Record fromCommandSave(RecordCommand.Save save, Users users) {
        return create(save.rating(),
                      save.comment(),
                      save.recordDate(),
                      users);
    }*/
}
