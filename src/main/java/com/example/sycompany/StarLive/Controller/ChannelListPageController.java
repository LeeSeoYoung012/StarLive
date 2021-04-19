package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Repository.ChannelRepository;
import com.example.sycompany.StarLive.Service.ViewSortService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.channels.Channels;
import java.util.List;

public class ChannelListPageController {

ChannelRepository channelRepository;
ViewSortService viewSortService;

    @GetMapping("/channels")
    public List<Channel>getChannelList(){

        List<Channel>channels = channelRepository.findAll();
        return viewSortService.compareChannelMemberCount(channels);
    }

    @GetMapping("/channels")
    public List<Channel>getChannelListByCreatedAt(@RequestParam("order") String type){

        List<Channel>channels = channelRepository.findAll();

        if(type.equals("latest")){
                return viewSortService.compareChannelByCreatedAt(channels);
            }

        else if(type.equals("abc")){
                return viewSortService.compareChannelByABC(channels);
            }

        else{
                return viewSortService.compareChannelMemberCount(channels);
            }
    }

}
