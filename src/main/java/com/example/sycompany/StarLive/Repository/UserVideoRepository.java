package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Entity.UserVideo;
import com.example.sycompany.StarLive.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVideoRepository extends JpaRepository<UserVideo,Long> {

}
