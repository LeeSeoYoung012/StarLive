package com.example.sycompany.StarLive.Example;

import com.example.sycompany.StarLive.DTO.UserDTO;

public class UserExample {


    public UserDTO UserDTOExample() {
        UserDTO userdto = new UserDTO();

        userdto.setId(1L);
        userdto.setEmail("lsy");
        userdto.setName("leeseoyoung");
        userdto.setPassword("123");

        return userdto;

    }

}
