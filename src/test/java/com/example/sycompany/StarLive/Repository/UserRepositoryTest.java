package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.DTO.UserDTO;
import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Service.UserService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Mock
    public UserRepository userRepository;

    private User user;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    @Test
    void findByUserIdTest() {

        MockitoAnnotations.openMocks(this);
        Mockito.when(userRepository.findByUserName("lsy")).thenReturn(TestUserEntity());
       // User user = userRepository.findByUserId("lsy"); //Test를 통해서 JPA 를 통해 db 조회가 잘 안되는 것을 확인
        User actual = userRepository.findByUserName("lsy");
        assertEquals(actual.getId(), 1);
        assertEquals(actual.getPassword(), "1234");
  assertEquals(actual.getEmail(), "nn");

        Mockito.verify(userRepository).findByUserName("lsy"); //findByUserId가 불렸는지 검증
    }

    private User TestUserEntity(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setPassword("1234");
        userDTO.setUserName("lsy");
        userDTO.setEmail("nn");
        User user =  new User(userDTO);
        return user;
    }


}