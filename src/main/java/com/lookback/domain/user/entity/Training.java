package com.lookback.domain.user.entity;

import com.lookback.domain.record.entity.Record;
import com.lookback.presentation.users.dto.SaveTrainingUserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static org.springframework.http.codec.ServerSentEvent.builder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "TRAINING")
public class Training {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users student;

    private String trainingStatus;

    public Training() {}

    public static Training create(Users trainer, Users student, String trainingStatus) {
        return builder().trainer(trainer)
                        .student(student)
                        .trainingStatus(trainingStatus)
                        .build();
    }

}
