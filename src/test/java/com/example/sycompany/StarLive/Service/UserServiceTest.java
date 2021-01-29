package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    void update() {
        UserDTO userdto = new UserDTO();
        userdto.setUserId("sy");
        userdto.setAddr("hh");
        userdto.setEmail("lsy");
        userdto.setName("ㅇㅅㅇ");
        userdto.setPhonenum("010");
        userdto.setPassword("123");
        Long i = valueOf(1);
        userdto.setId(i);
        Long res = userService.update(i,userdto);
        assertEquals(res,1);
    }
}