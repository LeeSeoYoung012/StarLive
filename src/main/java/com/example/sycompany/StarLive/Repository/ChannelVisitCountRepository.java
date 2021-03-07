package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.ChannelVisitCount;
import com.example.sycompany.StarLive.Entity.VideoViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ChannelVisitCountRepository extends JpaRepository<ChannelVisitCount,Long> {
    List<ChannelVisitCount> findByChannelAndChannelVisitDateBetween(Channel channel,LocalDate start, LocalDate end);
    List<ChannelVisitCount> findByChannel(Channel channel);
    ChannelVisitCount findByChannelVisitDate(LocalDate localDate);



}
