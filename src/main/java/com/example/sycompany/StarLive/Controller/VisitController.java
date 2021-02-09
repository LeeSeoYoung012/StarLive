package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Repository.ChannelRepository;
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
    ViewCountControlService viewCountControlService;

    //채널 클릭시 조회수 증가
    @PutMapping("/channelvisit/{channelId}")
    public Long putChannelVisitCount(@PathVariable Long channelId){
        Channel channel = channelRepository.findByChannelId(channelId);
        ChannelVisitCountDTO channelVisitCountDTO= viewCountControlService.ChannelVisitCountIncrease(channel);
        return viewCountControlService.updateChannelVisitCount(channelVisitCountDTO);
    }

    //채널의 총 조회수를 조회
    @GetMapping("/channelvisit/{channelId}")
    public Long getChannelVisitCount(@PathVariable Long channelId){
        Channel channel = channelRepository.findByChannelId(channelId);
        return viewCountControlService.ChannelVisitCountTotal(channel);
    }

    //
    @PutMapping("/videoview/{videoId}")
    public Long putVideoViewCount(@PathVariable Long videoId){

    }



}
