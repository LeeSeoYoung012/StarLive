package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Long> {

    List<Video> findAllByOrderByCreatedAt();
    Video findByVideoId(Long videoId);
    List<Video> findByChannel(Channel channel);

    @Query("select Count(m) from Video m where m.channel = :channel")
    Long findVideoCountByChannel(@Param("channel")Channel channel);




}
