package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.DTO.UserDTO;

import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Repository.UserRepository;
import com.example.sycompany.StarLive.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    UserController userController;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    UserDTO userDTO;

    @BeforeEach  //테스트 전 선작업
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userRepository, userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    private User TestUserEntity(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setPassword("1234");
        userDTO.setName("이서영");
        userDTO.setEmail("nn");
        User user =  new User(userDTO);
        return user;
    }



    @Test
    public void getUserResultMatch() throws Exception {
        Mockito.when(userRepository.findByUserName("sy")).thenReturn( TestUserEntity());
        MvcResult result =
                 (MvcResult) mockMvc.perform(MockMvcRequestBuilders.get("/user/sy")
                         .contentType(MediaType.APPLICATION_JSON))
                 .andExpect( status().isOk())
                 .andDo(print())
                 .andReturn();
        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userdto = objectMapper.readValue(content, UserDTO.class);
        System.out.println("JSon Result="+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userdto));

        assertEquals(userdto.getId(),1L);
        assertEquals(userdto.getPassword(),"1234");
        assertEquals(userdto.getName(),"이서영");
        assertEquals(userdto.getEmail(),"nn");
     }

    @Test
    void postUser() throws Exception {

        UserDTO userdto  = new UserDTO();
        userdto.setId(1L);;
        userdto.setEmail("lsy");
        userdto.setName("leeseoyoung");
        userdto.setPassword("123");
        User user=new User(userdto);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(userdto);
        MvcResult result =
                mockMvc.perform(
                        MockMvcRequestBuilders.post("/user")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect( MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();

        String rescontent = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO resuserdto = objectMapper.readValue(rescontent, UserDTO.class);
        assertEquals(resuserdto.getId(),1L);
        assertEquals(resuserdto.getPassword(),"123");
        assertEquals(resuserdto.getName(),"leeseoyoung");
        assertEquals(userdto.getEmail(),"lsy");
    }


}