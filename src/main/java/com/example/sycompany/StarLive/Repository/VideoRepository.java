package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Long> {

    List<Video> findAllByAndOrderByCreatedAt();


}
