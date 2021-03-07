package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.DTO.UserDTO;
import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Repository.UserRepository;
import com.example.sycompany.StarLive.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    //회원 user_Id에 해당하는 정보를 조회, 아이디 중복 확인

    @Autowired
    public UserController(
            final UserRepository userRepository,
            final UserService userService) {

        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(value = "/user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable String userName){
        User user = userRepository.findByUserName(userName);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //신규 가입자 추가
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> postUser(@RequestBody UserDTO userDTO){
        User user = new User(userDTO);
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //신규 가입자 정보 수정
    @PutMapping("/user")
    public Long putUser(@RequestBody UserDTO userDTO ){
        Long id = userDTO.getId();
        return userService.update(id, userDTO);
    }

    //회원 탈퇴
    @DeleteMapping("/user")
    public long deleteUser(@RequestBody String userName){
        User user = userRepository.findByUserName(userName);

            Long id = user.getId();
            userRepository.deleteById(id);
            return id;


    }


}