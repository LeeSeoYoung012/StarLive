package com.example.sycompany.StarLive.Entity;

import com.example.sycompany.StarLive.DTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor //기본생성자 생성
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String addr;

    @Column(nullable = false)
    private String phonenum;


    public User(UserDTO userDTO){
        this.id = userDTO.getId();
        this.userId = userDTO.getUserId();
        this.password = userDTO.getPassword();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.addr = userDTO.getAddr();
        this.phonenum = userDTO.getPhonenum();

    }

    public void update(UserDTO userDTO){
        this.id = userDTO.getId();
        this.userId = userDTO.getUserId();
        this.password = userDTO.getPassword();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.addr = userDTO.getAddr();
        this.phonenum = userDTO.getPhonenum();
    }

}
