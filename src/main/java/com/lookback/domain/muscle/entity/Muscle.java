package com.lookback.domain.muscle.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.muscle.command.MuscleCategoryCommand;
import com.lookback.domain.muscle.command.MuscleCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Muscle extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MUSCLE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUSCLE_GROUP_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MuscleGroup muscleGroup;

    private String muscleName;
    private String origin; //근육의 기시부(근육이 시작되는 부위)
    private String insertion; //근육의 정지부(근육이 끝나는 부위)
    private String role; //역할
    private String description;
    private Long activationLevel;
    private String createdBy;
    private String updatedBy;

    // 기본 생성자
    protected Muscle() {}

    // Excel 데이터를 위한 생성자
    public Muscle(String muscleName, String origin, String insertion, String role,
                  String description, Long activationLevel, MuscleGroup muscleGroup) {
        this.muscleName = muscleName;
        this.origin = origin;
        this.insertion = insertion;
        this.role = role;
        this.description = description;
        this.activationLevel = activationLevel;
        this.muscleGroup = muscleGroup;
        this.createdBy = "admin";
        this.updatedBy = "admin";
    }
    public static Muscle create( String muscleName, String origin, String insertion, String role,
                                 String description, Long activationLevel, MuscleGroup muscleGroup) {
        return builder().muscleName(muscleName)
                        .origin(origin)
                        .insertion(insertion)
                        .role(role)
                        .description(description)
                        .activationLevel(activationLevel)
                        .muscleGroup(muscleGroup)
                        .build();
    }

    public static Muscle fromCommandSave(MuscleCommand.Save save, MuscleGroup muscleGroup) {
        return create(save.muscleName(),
                      save.origin(),
                      save.insertion(),
                      save.role(),
                      save.description(),
                      save.activationLevel(),
                      muscleGroup);
    }
}
