package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import com.example.sycompany.StarLive.DTO.VideoViewCountDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Repository.ChannelRepository;
import com.example.sycompany.StarLive.Repository.VideoRepository;
import com.example.sycompany.StarLive.Service.ViewCountControlService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@NoArgsConstructor
public class VisitController {

    ChannelRepository channelRepository;
    VideoRepository videoRepository;
    ViewCountControlService viewCountControlService;

    //채널 클릭시 조회수 증가
    @PutMapping("/channelvisit/{channelId}")
    public Long putChannelVisitCount(@PathVariable Long channelId){
        Channel channel = channelRepository.findByChannelId(channelId);
        ChannelVisitCountDTO channelVisitCountDTO= viewCountControlService.channelVisitCountIncrease(channel);
        return viewCountControlService.updateChannelVisitCount(channelVisitCountDTO);
    }

    //채널의 총 조회수를 조회
    @GetMapping("/channelvisit/{channelId}")
    public Long getChannelVisitCount(@PathVariable Long channelId){
        Channel channel = channelRepository.findByChannelId(channelId);
        return viewCountControlService.channelVisitCountTotal(channel);
    }

    //비디오 클리시 조회수 증가
    @PutMapping("/videoview/{videoId}")
    public Long putVideoViewCount(@PathVariable Long videoId){

        Video video = videoRepository.findByVideoId(videoId);
        VideoViewCountDTO videoViewCountDTO = viewCountControlService.videoViewCountIncrease(video);
        return viewCountControlService.updateVideoViewCount(videoViewCountDTO);

    }

    //비디오 총 조회수 조회
    @GetMapping("/videoview/{videoId}")
    public Long getVideoViewCount(@PathVariable Long videoId){
        Video video = videoRepository.findByVideoId(videoId);
        return viewCountControlService.videoViewCountTotal(video);
    }




}
