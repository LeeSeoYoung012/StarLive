package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    public UserRepository userRepository;


    @Test
    void findByUserIdTest() {

        User user = userRepository.findByUserId("lsy"); //Test를 통해서 JPA 를 통해 db 조회가 잘 안되는 것을 확인
        assertEquals(user.getId(), 1);
        assertEquals(user.getPassword(), "1234");
        assertEquals(user.getUserId(),"lsy");
        assertEquals(user.getAddr(),"aa");
        assertEquals(user.getName(),"이서영");
        assertEquals(user.getEmail(), "nn");
        assertEquals(user.getPhonenum(),"01094796540");

    }


}