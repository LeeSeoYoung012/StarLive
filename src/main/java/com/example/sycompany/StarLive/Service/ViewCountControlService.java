package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.ChannelDTO;
import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.ChannelVisitCount;
import com.example.sycompany.StarLive.Repository.ChannelVisitCountRepository;
import com.example.sycompany.StarLive.Repository.VideoViewsRepository;

import java.time.LocalDate;
import java.util.List;

public class ViewCountControlService {
    VideoViewsRepository videoViewsRepository;
    ChannelVisitCountRepository channelVisitCountRepository;

    //채널의 조회수 증가
    public ChannelVisitCountDTO ChannelVisitCountIncrease(Channel channel ){
        LocalDate now = LocalDate.now();
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.makeEntityToDTO(channel);
        ChannelVisitCount nowvisit =  channelVisitCountRepository.findByChannelVisitDate(now);
        ChannelVisitCountDTO nowvisitDTO = new ChannelVisitCountDTO();
        Long visitcount = 0L;
        if( nowvisit !=null){
           nowvisitDTO.makeEntityToDTO(nowvisit);
           visitcount = nowvisitDTO.getChannelVisitCount();
           nowvisitDTO.setChannelVisitCount(visitcount+1);
        }
       else{
            nowvisitDTO.setChannel(channel);
            nowvisitDTO.setChannelVisitCount(1L);
            nowvisitDTO.setChannelVisitDate(LocalDate.now());

       }
    return nowvisitDTO;

    }

    //채널의 전체 조회수 조회
    public Long ChannelVisitCountTotal(Channel channel){

        List<ChannelVisitCount> channelList =  channelVisitCountRepository.findByChannel(channel);
        ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO();
        ChannelVisitCount channelVisitCount;
        Long totalCount=0L;
        for(int i=0; i<channelList.size(); i++) {
            channelVisitCount = channelList.get(i);
            if (channelVisitCount != null) {
                channelVisitCountDTO.makeEntityToDTO(channelVisitCount);
                totalCount += channelVisitCountDTO.getChannelVisitCount();
            }
        }

        return totalCount;

    }

    public Long updateChannelVisitCount (ChannelVisitCountDTO channelVisitCountDTO){
        if(channelVisitCountDTO.getChannelVisitId()==null){
               ChannelVisitCount channelVisitCount= new ChannelVisitCount(channelVisitCountDTO);
               channelVisitCountRepository.save(channelVisitCount);
               return -1L;
        }
        else{
            ChannelVisitCount channelVisitCount = channelVisitCountRepository.findById(channelVisitCountDTO.getChannelVisitId())
                    .orElseThrow(()-> new NullPointerException("해당 날짜와 채널의 조회수가 존재하지 않습니다."));
            channelVisitCount.update(channelVisitCountDTO);
            return channelVisitCountDTO.getChannelVisitId();
        }

    }



}
