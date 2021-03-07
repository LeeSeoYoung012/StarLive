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
import com.example.sycompany.StarLive.Repository.UserRepository;
import com.example.sycompany.StarLive.Repository.VideoViewsRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ViewCountControlService {
    final VideoViewsRepository videoViewsRepository;
    final ChannelVisitCountRepository channelVisitCountRepository;


    //채널의 조회수 증가
    public ChannelVisitCountDTO channelVisitCountIncrease(Channel channel ){
        LocalDate now = LocalDate.now();
        ChannelVisitCount nowvisit =  channelVisitCountRepository.findByChannelVisitDate(now);

        Long visitcount = 0L;
        if( nowvisit !=null){
            ChannelVisitCountDTO nowvisitDTO= new ChannelVisitCountDTO(nowvisit);
           visitcount = nowvisitDTO.getChannelVisitCount();
           nowvisitDTO.setChannelVisitCount(visitcount+1);
           return nowvisitDTO;
        }
       else{
            ChannelVisitCountDTO nowvisitDTO = new ChannelVisitCountDTO();
            nowvisitDTO.setChannel(channel);
            nowvisitDTO.setChannelVisitCount(1L);
            nowvisitDTO.setChannelVisitDate(LocalDate.now());
            return nowvisitDTO;

       }
    }

    //채널의 전체 조회수 조회
    public Long channelVisitCountTotal(Channel channel){

        List<ChannelVisitCount> channelList =  channelVisitCountRepository.findByChannel(channel);
        ChannelVisitCount channelVisitCount;

        Long totalCount=0L;
        for(int i=0; i<channelList.size(); i++) {
            channelVisitCount = channelList.get(i);
            if (channelVisitCount != null) {
                ChannelVisitCountDTO  channelVisitCountDTO= new ChannelVisitCountDTO(channelVisitCount);
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
        VideoViewCount nowview =  videoViewsRepository.findByViewsDate(now);
       // VideoViewCountDTO nowviewDTO = new VideoViewCountDTO();

        Long visitcount = 0L;
        if( nowview !=null){
            VideoViewCountDTO nowviewDTO = new VideoViewCountDTO(nowview);
            visitcount = nowviewDTO.getViewsCount();
            nowviewDTO.setViewsCount(visitcount+1);
            return nowviewDTO;
        }
        else{
            VideoViewCount nowViewIfNull = new VideoViewCount();
            VideoViewCountDTO nowviewDTOIfNull = new VideoViewCountDTO(nowViewIfNull);
            nowviewDTOIfNull.setVideo(video);
            nowviewDTOIfNull.setViewsCount(1L);
            nowviewDTOIfNull.setViewsDate(LocalDate.now());
            return nowviewDTOIfNull;

        }

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
        VideoViewCount videoViewCount;
        Long totalCount=0L;
        for(int i=0; i< videoList.size(); i++) {
            videoViewCount =  videoList.get(i);
            if (videoViewCount != null) {
                VideoViewCountDTO videoViewCountDTO= new VideoViewCountDTO(videoViewCount);
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
