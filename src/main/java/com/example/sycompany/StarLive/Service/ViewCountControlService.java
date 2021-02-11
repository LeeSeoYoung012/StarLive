package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.ChannelDTO;
import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import com.example.sycompany.StarLive.DTO.VideoDTO;
import com.example.sycompany.StarLive.DTO.VideoViewCountDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.ChannelVisitCount;
import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoViewCount;
import com.example.sycompany.StarLive.Repository.ChannelVisitCountRepository;
import com.example.sycompany.StarLive.Repository.VideoViewsRepository;

import java.time.LocalDate;
import java.util.List;

public class ViewCountControlService {
    VideoViewsRepository videoViewsRepository;
    ChannelVisitCountRepository channelVisitCountRepository;

    //채널의 조회수 증가
    public ChannelVisitCountDTO channelVisitCountIncrease(Channel channel ){
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
    public Long channelVisitCountTotal(Channel channel){

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


    //수정된 채널 조회수를 업데이트 혹은 추가
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

    //비디오 조회수 증가
    public VideoViewCountDTO videoViewCountIncrease(Video video){
        LocalDate now = LocalDate.now();
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.makeEntityToDTO(video);
        VideoViewCount nowview =  videoViewsRepository.findByViewDate(now);
        VideoViewCountDTO nowviewDTO = new VideoViewCountDTO();
        Long visitcount = 0L;
        if( nowview !=null){
            nowviewDTO.makeEntityToDTO(nowview);
            visitcount = nowviewDTO.getViewsCount();
            nowviewDTO.setViewsCount(visitcount+1);
        }
        else{
            nowviewDTO.setVideo(video);
            nowviewDTO.setViewsCount(1L);
            nowviewDTO.setViewsDate(LocalDate.now());

        }
        return nowviewDTO;
    }


    //수정된 비디오 조회수를 업데이트 혹은 추가
    public Long updateVideoViewCount (VideoViewCountDTO videoViewCountDTO){
        if(videoViewCountDTO.getViewId()==null){
            VideoViewCount videoViewCount= new VideoViewCount(videoViewCountDTO);
            videoViewsRepository.save(videoViewCount);
            return -1L;
        }
        else{
            VideoViewCount videoViewCount = videoViewsRepository.findById(videoViewCountDTO.getViewId())
                    .orElseThrow(()-> new NullPointerException("해당 날짜와 채널의 조회수가 존재하지 않습니다."));
            videoViewCount.update(videoViewCountDTO);
            return videoViewCountDTO.getViewId();
        }

    }


    //비디오의 전체 조회수 조회
    public Long videoViewCountTotal(Video video){

        List<VideoViewCount> videoList =  videoViewsRepository.findByVideo(video);
        VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO();
        VideoViewCount videoViewCount;
        Long totalCount=0L;
        for(int i=0; i< videoList.size(); i++) {
            videoViewCount =  videoList.get(i);
            if (videoViewCount != null) {
                videoViewCountDTO.makeEntityToDTO(videoViewCount);
                totalCount += videoViewCountDTO.getViewsCount();
            }
        }
         return totalCount;

    }

    public Long severalVideosViewCountTotal(List<Video> videos){
        Video video;
        Long totalCount=0L;
        for(int i=0; i<videos.size(); i++){

            video = videos.get(i);
            if(video!=null){
                totalCount += videoViewCountTotal(video);
            }
        }
        return totalCount;
    }



}
