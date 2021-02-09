package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.UserSubscribeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscribeListRepsitory extends JpaRepository<UserSubscribeList,Long> {

    List<UserSubscribeList>findByUserNumEquals(Long user_num);

}
