package com.lookback.domain.record.dto;

import com.lookback.domain.common.constant.enums.UserTypeEnum;
import com.lookback.domain.user.entity.Trainer;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UsersDomainDto {

    private Long userId;

    private String kakaoId;

    private String email;
    private String password;
    private String phone;
    private Long birthDt;
    private double weight;
    private double height;
    private String snsProvider;
    private String snsId;
    private String userName;
    private String nickName;
    private String profileImageUrl;
    private String verified; //본인인증 여부
    private String gender;
    private LocalDateTime signupDate; //가입일
    private LocalDateTime lastLoginDate;
    private String status; //
    private UserTypeEnum userType;
    private String isProfileComplete;

    public UsersDomainDto(String nickName, String userName, String isProfileComplete) {
        this.nickName = nickName;
        this.userName = userName;
        this.isProfileComplete = isProfileComplete;
    }

    public static UsersDomainDto createDto(Long userId, String userName, String nickName) {
        return UsersDomainDto
                .builder()
                .userId(userId)
                .userName(userName)
                .nickName(nickName)
                .build();
    }

    public String getUserTypeStr() {
        return this.userType != null? this.userType.toString() : null;
    }

}
