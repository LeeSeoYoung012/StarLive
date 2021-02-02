package com.example.sycompany.StarLive.DTO;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@Builder
class UserDTOTest {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String addr;
    private String phonenum;


    @Test
    void setId(Long id) {
        this.id = id;
    }

    @Test
    void setUserId(String userId) {
        this.userId = userId;
    }

    @Test
    void setPassword(String password) {
        this.password = password;
    }

    @Test
    void setName(String name) {
        this.name = name;
    }

    @Test
    void setEmail(String email) {
        this.email = email;
    }

    @Test
    void setAddr(String addr) {
        this.addr =addr;
    }

    @Test
    void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    @Test
    Long getId() {
        return this.id;
    }

    @Test
    String getUserId() {
        return this.userId;
    }

    @Test
    String getPassword() {
        return this.password;
    }

    @Test
    String getName() {
        return this.name;
    }

    @Test
    String getEmail() {
        return this.email;
    }

    @Test
    String getAddr() {
        return this.addr;
    }

    @Test
    String getPhonenum() {
        return this.phonenum;
    }
}