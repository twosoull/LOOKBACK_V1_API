package com.lookback.domain.record.entity;

import com.lookback.domain.muscle.command.MuscleCommand;
import com.lookback.domain.muscle.entity.Muscle;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.record.command.RecordCommand;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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

    private Long rating;
    private String comment;
    private LocalDateTime recordDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Record() {}

    public static Record create(Long rating, String comment, LocalDateTime recordDate, Users users) {
        return builder().rating(rating)
                        .comment(comment)
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
