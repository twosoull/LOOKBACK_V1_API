package com.lookback.presentation.users.dto.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;  //30분 jwt
    private String refreshToken; //30일 jwt (db저장)
}
