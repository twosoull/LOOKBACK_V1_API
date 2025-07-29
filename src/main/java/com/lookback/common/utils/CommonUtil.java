package com.lookback.common.utils;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    private static Environment environment; // static으로 저장

    public CommonUtil(Environment env) {
        CommonUtil.environment = env; // 생성자에서 static 필드에 주입
    }

    public static boolean isLocalProfile() {
        for (String profile : environment.getActiveProfiles()) {
            if ("local".equalsIgnoreCase(profile)) {
                return true;
            }
        }
        return false;
    }
}
