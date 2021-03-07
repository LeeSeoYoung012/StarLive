package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.DTO.ChannelDTO;
import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import com.example.sycompany.StarLive.DTO.UserSubscribeListDTO;
import com.example.sycompany.StarLive.Entity.*;
import com.example.sycompany.StarLive.Repository.ChannelRepository;
import com.example.sycompany.StarLive.Repository.UserRepository;
import com.example.sycompany.StarLive.Repository.UserSubscribeListRepsitory;
import com.example.sycompany.StarLive.Repository.VideoRepository;
import com.example.sycompany.StarLive.Service.UserService;
import com.example.sycompany.StarLive.Service.ViewCountControlService;
import com.example.sycompany.StarLive.Service.ViewSortService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@NoArgsConstructor
public class MainPageController {

    ViewSortService viewSortService;
    VideoRepository videoRepository;
    ChannelRepository channelRepository;
    UserSubscribeListRepsitory userSubscribeListRepsitory;
    ViewCountControlService viewCountControlService;


    @Autowired
    public MainPageController(
            VideoRepository videoRepository,
            ChannelRepository channelRepository, ViewCountControlService viewCountControlService, ViewSortService viewSortService,  UserSubscribeListRepsitory userSubscribeListRepsitory) {

        this.videoRepository = videoRepository;
        this.channelRepository= channelRepository;
        this.viewCountControlService = viewCountControlService;
        this.viewSortService = viewSortService;
        this.userSubscribeListRepsitory = userSubscribeListRepsitory;
    }

    //일월별 영상 조회수 대로 정렬 출력
    @GetMapping(value = "/home/chart/video/{days}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Video> getVideosOrderByViewCount(@PathVariable int days){


        List<Video> videoList = videoRepository.findAll();
        return viewSortService.compareVideoViewCount(videoList,days);
    }

    //일월별 채널 조회수 대로 정렬 출력
    @GetMapping(value = "/home/chart/channel/{days}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Channel> getChannelsOrderByChannelCount(@PathVariable int days){

        List<Channel> channelList = channelRepository.findAll();
        return viewSortService.compareChannelVisitCount(channelList,days);

    }

    //채널 클릭시 조회수 증가
    @PutMapping("/home/chart/channel/{channelId}")
    public Long putChannelVisitCount(@PathVariable Long channelId){
        Channel channel = channelRepository.findByChannelId(channelId);
        ChannelVisitCountDTO channelVisitCountDTO= viewCountControlService.channelVisitCountIncrease(channel);
        return viewCountControlService.updateChannelVisitCount(channelVisitCountDTO);
    }

   //최근에 올린 영상 순서대로 출력
    @GetMapping("/home/new")
    public List<Video> getVideosOrderByCreatedAt(){
        return videoRepository.findAllByOrderByCreatedAt();
    }

    //내가 구독한 채널 출력
    @GetMapping("/home/my/{user_num}")
    public List<Long> getChannelUserSubscribed(@PathVariable Long user_num){

       List<UserSubscribeList> subscribeLists =userSubscribeListRepsitory.findByUserNumEquals(user_num);
       UserSubscribeList userSubscribeList = new UserSubscribeList();
       UserSubscribeListDTO userSubscribeListDTO;
       Channel channel;
       ChannelDTO channelDTO = new ChannelDTO();
       List<Long> channelList = new ArrayList<>();

       for(int i=0; i<subscribeLists.size(); i++){
           userSubscribeList = subscribeLists.get(i);
           userSubscribeListDTO = new UserSubscribeListDTO(userSubscribeList);
           channel = userSubscribeListDTO.getChannel();
           channelDTO= new ChannelDTO(channel);
           channelList.add(channelDTO.getChannelId());
       }
            return channelList;
    }



}

