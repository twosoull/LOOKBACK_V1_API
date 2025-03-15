package com.lookback.domain.record.entity;

import com.lookback.domain.record.command.RecordCommand;
import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(name = "RECORD")
public class Record {

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Record() {}

    public static Record create(Long rating, String comment, LocalDate recordDate, Users users) {
        return builder().comment(comment)
                        .recordDate(recordDate)
                        .users(users)
                        .build();
    }

    public static Record fromCommandSave(RecordCommand.Save save, Users users) {
        return create(save.rating(),
                      save.comment(),
                      save.recordDate(),
                      users);
    }
}
