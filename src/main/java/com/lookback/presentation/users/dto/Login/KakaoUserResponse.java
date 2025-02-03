package com.lookback.presentation.users.dto.Login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KakaoUserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @JsonProperty("properties")
    private KakoProperties properties;

    @Data
    public static class KakaoAccount {
        private String email;
    }

    @Data
    public static class KakoProperties {
        private String nickName;

        @JsonProperty("profile_image")
        private String profileImage;
    }
}
