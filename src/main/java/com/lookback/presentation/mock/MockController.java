package com.lookback.presentation.mock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @GetMapping("/private/mock/loginStatus")
    public String loginStatus() {
        return "Ok";
    }
}
