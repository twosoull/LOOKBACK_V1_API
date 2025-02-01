package com.lookback.presentation.users.dto;

import com.lookback.domain.user.entity.Users;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsersDto {
    private Long userId;
    private String email;
    private String password;
    private String phone;
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
    private String status; //계정상태
    private String trainerYn;

    public static UsersDto fromEntity(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(users.getId());
        usersDto.setEmail(users.getEmail());
        usersDto.setPassword(users.getPassword());
        usersDto.setPhone(users.getPhone());
        usersDto.setWeight(users.getWeight());
        usersDto.setHeight(users.getHeight());
        usersDto.setSnsProvider(users.getSnsProvider());
        usersDto.setSnsId(users.getSnsId());
        usersDto.setUserName(users.getUserName());
        usersDto.setNickName(users.getNickName());
        usersDto.setProfileImageUrl(users.getProfileImageUrl());
        usersDto.setVerified(users.getVerified());
        usersDto.setGender(users.getGender());
        usersDto.setSignupDate(users.getSignupDate());
        usersDto.setLastLoginDate(users.getLastLoginDate());
        usersDto.setStatus(users.getStatus());
        usersDto.setTrainerYn(users.getTrainerYn());
        return usersDto;
    }
}
