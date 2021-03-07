package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.VideoDTO;
import com.example.sycompany.StarLive.Entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelPageService {

    public Long getTotalChannelVideoLikes(List<Video> videos){

        Video video;
        Long totalLikes = 0L;
        for(int i=0; i<videos.size(); i++){
            video = videos.get(i);
            if(video!=null){
                VideoDTO videoDTO = new VideoDTO(video);
                totalLikes += videoDTO.getLikeCount();
            }
        }
        return totalLikes;

    }




}
