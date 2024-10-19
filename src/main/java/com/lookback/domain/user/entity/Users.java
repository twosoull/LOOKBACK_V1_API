package com.lookback.domain.user.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class Users {

    @Id @GeneratedValue
    @Column(name = "USERS_ID")
    private Long id;

    private String email;
    private String password;
    private String snsProvider;
    private String snsId;
    private String userName;
    private String nickName;
    private String profileImageUrl;
    private String phone;
    private String verified; //본인인증 여부
    private String gender;
    private LocalDateTime signupDate; //가입일
    private LocalDateTime lastLoginDate;
    private String status; //계정상태
    private LocalDateTime updatedAt;

}
