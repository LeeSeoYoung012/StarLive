package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Long> {

    List<Video> findAllByAndOrderByCreatedAt();
    Video findByVideoId(Long videoId);
    List<Video> findByChannelId(Long channelId);

    @Query("select Count(m) from Video m where m.channelId = :channelId")
    Long findVideoCountByChannelId(@Param("channelId")Long channelId);




}
