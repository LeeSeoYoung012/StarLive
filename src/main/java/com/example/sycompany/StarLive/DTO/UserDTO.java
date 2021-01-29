package com.example.sycompany.StarLive.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
public class UserDTO {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String addr;
    private String phonenum;

}
