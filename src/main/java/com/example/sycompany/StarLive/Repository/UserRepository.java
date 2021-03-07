package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {
 //List<User> findAllByUser_IdOrderByIdAsc(String user_id);
 User findByUserName(String user_id);


}
