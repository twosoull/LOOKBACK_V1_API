package com.lookback.domain.muscle.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.muscle.command.MuscleCategoryCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Table(name = "MUSCLE_CATEGORY")
@AllArgsConstructor
@Getter
public class MuscleCategory extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MUSCLE_CATEGORY_ID")
    private Long id;

    private String muscleName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<MuscleCategory> parent; // 부모 카테고리 (상체, 하체 등 계층 구조)

    private String muscleCategoryName;
    private String description;
    private String createdBy;
    private String updatedBy;

    // 기본 생성자
    protected MuscleCategory() {}

    // Excel 데이터를 위한 생성자
    public MuscleCategory(String muscleCategoryName, String description) {
        this.muscleCategoryName = muscleCategoryName;
        this.description = description;
        this.createdBy = "admin";
        this.updatedBy = "admin";
    }

    public static MuscleCategory create(String muscleCategoryName, String description) {
        return builder()
                .muscleCategoryName(muscleCategoryName)
                .description(description).build();
    }

    public static MuscleCategory fromCommandSave(MuscleCategoryCommand.Save save) {
        return create(save.muscleCategoryName(), save.description());
    }


}
