package com.lookback.presentation.users.dto.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KakaoUser {
    private String email;
    private String nickName;
    private String profileImg;
}
