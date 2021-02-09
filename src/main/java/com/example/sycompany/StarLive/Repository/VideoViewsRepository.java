package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.VideoViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VideoViewsRepository extends JpaRepository<VideoViewCount,Long> {

   VideoViewCount findByViewDate(LocalDateTime date);
   List<VideoViewCount> findAllByVideoIdAndViewsDateBetweeen(Long video_id, LocalDateTime start, LocalDateTime end);

}
