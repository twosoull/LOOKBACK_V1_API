package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.CenterProductType;
import com.lookback.presentation.manager.center.dto.SaveCenterProductRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CenterProduct extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CENTER_PRODUCT_ID")
    private Long id;

    private String centerProductName;               // 센터_상품_이름
    private String centerProductUsagePeriod;        // 센터_상품_이용기간
    private String centerProductPricingBasisType;   // 센터_상품_가격기준_타입
    private String centerProductOriPrice;           // 센터_상품_정가
    private String centerProductDiscountPrice;      // 센터_상품_할인가
    private String centerProductDiscountRate;       // 센터_상품_할인율
    private String centerProductNotice;             // 센터_상품_안내사항
    private String centerProductUseYn;              // 센터_상품_할인_사용여부

    @Enumerated(EnumType.STRING)
    private CenterProductType centerProductType;               // 센터_상품_구분_유형

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "centerProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CenterProductOption> centerProductOptions = new ArrayList<>(); // 센터_상품_옵션

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Center center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTER_TRAINER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CenterTrainer centerTrainer;

    public void update(SaveCenterProductRequest dto) {
        this.centerProductName = dto.getCenterProductName();
        this.centerProductUsagePeriod = dto.getCenterProductUsagePeriod();
        this.centerProductPricingBasisType = dto.getCenterProductPricingBasisType();
        this.centerProductOriPrice = dto.getCenterProductOriPrice();
        this.centerProductDiscountPrice = dto.getCenterProductDiscountPrice();
        this.centerProductDiscountRate = dto.getCenterProductDiscountRate();
        this.centerProductNotice = dto.getCenterProductNotice();
        this.centerProductUseYn = dto.getCenterProductUseYn();
        this.centerProductType = dto.getCenterProductType();

        // 옵션 초기화 후 새로 설정 (연관관계 주의)
        if (dto.getCenterProductOptions() != null) {
            this.centerProductOptions.clear();
            dto.getCenterProductOptions().forEach(operateDto ->
                    this.centerProductOptions.add(operateDto.toEntity(this))
            );
        }
    }

}
