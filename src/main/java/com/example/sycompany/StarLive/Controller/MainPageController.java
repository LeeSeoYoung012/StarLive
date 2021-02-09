package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.DTO.ChannelDTO;
import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import com.example.sycompany.StarLive.DTO.UserSubscribeListDTO;
import com.example.sycompany.StarLive.Entity.*;
import com.example.sycompany.StarLive.Repository.ChannelRepository;
import com.example.sycompany.StarLive.Repository.UserRepository;
import com.example.sycompany.StarLive.Repository.UserSubscribeListRepsitory;
import com.example.sycompany.StarLive.Repository.VideoRepository;
import com.example.sycompany.StarLive.Service.MainPageService;
import com.example.sycompany.StarLive.Service.ViewCountControlService;
import com.example.sycompany.StarLive.Service.ViewSortService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@NoArgsConstructor
public class MainPageController {

    ViewSortService viewSortService;
    VideoRepository videoRepository;
    ChannelRepository channelRepository;
    UserSubscribeListRepsitory userSubscribeListRepsitory;
    ViewCountControlService viewCountControlService;



    //일월별 영상 조회수 대로 정렬 출력
    @GetMapping("/home/chart/video/{days}")
    public List<Video> getVideosOrderByViewCount(@PathVariable int days){

        List<Video> videoList = videoRepository.findAll();
        return viewSortService.compareVideoViewCount(videoList,days);

    }

    //일월별 채널 조회수 대로 정렬 출력
    @GetMapping("/home/chart/channel/{days}")
    public List<Channel> getChannelsOrderByChannelCount(@PathVariable int days){

        List<Channel> channelList = channelRepository.findAll();
        return viewSortService.compareChannelVisitCount(channelList,days);

    }

    //채널 클릭시 조회수 증가
    @PutMapping("/home/chart/channel/{channelId}")
    public Long putChannelVisitCount(@PathVariable Long channelId){
        Channel channel = channelRepository.findByChannelId(channelId);
        ChannelVisitCountDTO channelVisitCountDTO= viewCountControlService.ChannelVisitCountIncrease(channel);
        return viewCountControlService.updateChannelVisitCount(channelVisitCountDTO);
    }

   //최근에 올린 영상 순서대로 출력
    @GetMapping("/home/new")
    public List<Video> getVideosOrderByCreatedAt(){
        return videoRepository.findAllByAndOrderByCreatedAt();
    }

    //내가 구독한 채널 출력
    @GetMapping("/home/my/{user_num}")
    public List<Long> getChannelUserSubscribed(@PathVariable Long user_num){

       List<UserSubscribeList> subscribeLists =userSubscribeListRepsitory.findByUserNumEquals(user_num);
       UserSubscribeList userSubscribeList = new UserSubscribeList();
       UserSubscribeListDTO userSubscribeListDTO= new UserSubscribeListDTO();
       Channel channel = new Channel();
       ChannelDTO channelDTO = new ChannelDTO();
       List<Long> channelList = new ArrayList<>();
       for(int i=0; i<subscribeLists.size(); i++){
           userSubscribeList = subscribeLists.get(i);
           userSubscribeListDTO.makeEntityToDTO(userSubscribeList);
           channel = userSubscribeListDTO.getChannel();
           channelDTO.makeEntityToDTO(channel);
           channelList.add(channelDTO.getChannelId());
       }
            return channelList;
    }



}

