package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Repository.ChannelRepository;
import com.example.sycompany.StarLive.Repository.CommentsRepository;
import com.example.sycompany.StarLive.Repository.VideoRepository;
import com.example.sycompany.StarLive.Service.ChannelPageService;
import com.example.sycompany.StarLive.Service.ViewCountControlService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@NoArgsConstructor
@RestController
public class ChannelPageController {

    VideoRepository videoRepository;
    ChannelRepository channelRepository;
    ViewCountControlService viewCountControlService;
    ChannelPageService channelPageService;
    CommentsRepository commentsRepository;
    @GetMapping("/channel/{channelId}")
    public Long getChannelVideoCount(@PathVariable Long channelId, @RequestParam String infotype){
        List<Video> videos= videoRepository.findByChannelId(channelId);
        Channel channel = channelRepository.findByChannelId(channelId);
        switch (infotype) {
            case "videoCount":
                Long count = videoRepository.findVideoCountByChannelId(channelId);
                return count;
            case "videoViewCount":
                return viewCountControlService.severalVideosViewCountTotal(videos);
            case "likes":
                return channelPageService.getTotalChannelVideoLikes(videos);
            case "starLettersCount":
                return commentsRepository.findCommentsCountByChannelIdAndStarOrFan(channel,"s");
            case "commentsCount":
                return commentsRepository.findCommentsCountByChannelIdAndStarOrFan(channel,"f");

        }
        return -1L;
    }


}
