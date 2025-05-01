package com.lookback.presentation.users.dto;

import com.lookback.common.converter.CommonConverter;
import com.lookback.domain.record.dto.UsersDomainDto;
import com.lookback.domain.user.entity.Users;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private Long userId;
    private String email;
    private String password;
    private String phone;
    private Long age;
    private String birthDt;
    private double weight;
    private double height;
    private String snsProvider;
    private String snsId;
    private String userName;
    private String nickName;
    private String profileImageUrl;
    private String verified; //본인인증 여부
    private String gender;
    private String lastLessonDate;
    private LocalDateTime signupDate; //가입일
    private LocalDateTime lastLoginDate;
    private String status; //계정상태
    private String userType;

    public static UsersDto fromEntity(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(users.getId());
        usersDto.setEmail(users.getEmail());
        usersDto.setPassword(users.getPassword());
        usersDto.setPhone(users.getPhone());
        usersDto.setBirthDt(CommonConverter.formatDataOfDot(users.getBirthDt()));
        usersDto.setAge(CommonConverter.ageConverter(users.getBirthDt()));
        usersDto.setWeight(users.getWeight());
        usersDto.setHeight(users.getHeight());
        usersDto.setSnsProvider(users.getSnsProvider());
        usersDto.setSnsId(users.getSnsId());
        usersDto.setUserName(users.getUserName());
        usersDto.setNickName(users.getNickName());
        usersDto.setProfileImageUrl(users.getProfileImageUrl());
        usersDto.setVerified(users.getVerified());
        usersDto.setGender(CommonConverter.convertGender(users.getGender()));
        usersDto.setSignupDate(users.getSignupDate());
        usersDto.setLastLoginDate(users.getLastLoginDate());
        usersDto.setStatus(users.getStatus());
        usersDto.setUserType(users.getUserTypeStr());
        //usersDto.setLastLessonDate(users.getRe== null ? "" : users.getLastLoginDate().toString());
        return usersDto;
    }

    public static UsersDto fromDomainDto(UsersDomainDto users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(users.getUserId());
        usersDto.setEmail(users.getEmail());
        usersDto.setPassword(users.getPassword());
        usersDto.setPhone(users.getPhone());
        usersDto.setBirthDt(CommonConverter.formatDataOfDot(users.getBirthDt()));
        usersDto.setAge(CommonConverter.ageConverter(users.getBirthDt()));
        usersDto.setWeight(users.getWeight());
        usersDto.setHeight(users.getHeight());
        usersDto.setSnsProvider(users.getSnsProvider());
        usersDto.setSnsId(users.getSnsId());
        usersDto.setUserName(users.getUserName());
        usersDto.setNickName(users.getNickName());
        usersDto.setProfileImageUrl(users.getProfileImageUrl());
        usersDto.setVerified(users.getVerified());
        usersDto.setGender(CommonConverter.convertGender(users.getGender()));
        usersDto.setSignupDate(users.getSignupDate());
        usersDto.setLastLoginDate(users.getLastLoginDate());
        usersDto.setStatus(users.getStatus());
        usersDto.setUserType(users.getUserTypeStr());
        //usersDto.setLastLessonDate(users.getRe== null ? "" : users.getLastLoginDate().toString());
        return usersDto;
    }

    public static UsersDto of(String userName, String nickName) {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserName(userName);
        usersDto.setNickName(nickName);
        return usersDto;
    }

}
