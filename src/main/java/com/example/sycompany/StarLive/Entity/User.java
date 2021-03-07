package com.example.sycompany.StarLive.Entity;

import com.example.sycompany.StarLive.DTO.UserDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor //기본생성자 생성
@Entity
@Data
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;
    private String password;
    private String email;
    private String role;
    private String provider;
    private String providerId;


    @CreationTimestamp
    private Timestamp createDate;

    @Builder //?
    public User(String userName,String password,String email,String role,String provider, String providerId,Timestamp createDate) {
        //this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }

    public User(UserDTO userDTO){
        this.id = userDTO.getId();
        this.userName = userDTO.getUserName();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();



    }

    public void update(UserDTO userDTO){
        this.id = userDTO.getId();
        this.userName = userDTO.getUserName();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();

    }

}
