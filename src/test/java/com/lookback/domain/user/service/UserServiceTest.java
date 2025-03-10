package com.lookback.domain.user.service;

import com.lookback.domain.user.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void findAllUser(){
        //List<Users> allUsers = userService.findAllUsers();
        //log.info(allUsers.toString());
    }
}