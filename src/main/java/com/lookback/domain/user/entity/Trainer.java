package com.lookback.domain.user.entity;

import com.lookback.common.BaseEntity;
import com.lookback.presentation.manager.trainer.dto.SaveTrainerProfileRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.List;

@Entity
@Table(name = "TRAINER")
@Getter
@Setter
public class Trainer extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "TRAINER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users user;

    @OneToMany(mappedBy = "trainer")
    private List<Training> training;

    @OneToMany(mappedBy= "trainer")
    private List<TrainerInfo> trainerInfos;

    private String trainerOneLineIntro; // 한줄소개
    private String selfIntroduction;    // 자기소개
    private String trainerProfileShowYn;


    public void update(Trainer trainer, SaveTrainerProfileRequest saveTrainerProfileRequest) {
        this.trainerOneLineIntro = saveTrainerProfileRequest.getTrainerOneLineIntro();
        this.selfIntroduction = saveTrainerProfileRequest.getSelfIntroduction();
        this.trainerProfileShowYn = saveTrainerProfileRequest.getTrainerProfileShowYn();

        this.trainerInfos.clear();
        this.trainerInfos = saveTrainerProfileRequest.getTrainerInfos().stream().map(
                info -> info.toEntity(trainer)
                ).toList();
    }
}
