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
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class ViewSortService{
final VideoViewsRepository videoViewsRepository;
final ChannelVisitCountRepository channelVisitCountRepository;

public List<Video> compareVideoViewCount(List<Video> videos, int days)
    {
Collections.sort(videos, (video1, video2) -> {

    VideoDTO videoDTO1 = new VideoDTO();
    videoDTO1.makeEntityToDTO(video1);
    VideoDTO videoDTO2 = new VideoDTO();
    videoDTO2.makeEntityToDTO(video2);

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime before = now.minusDays(days);

    List<VideoViewCount> video1ViewCountlist = videoViewsRepository.findAllByVideoIdAndViewsDateBetweeen
            (videoDTO1.getVideoId(),before,now);

    Long video1ViewCount =0L;
    for(int i=0; i<video1ViewCountlist.size(); i++){
        VideoViewCount viewcount = video1ViewCountlist.get(i);
        VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO();
        videoViewCountDTO.makeEntityToDTO(viewcount);
        video1ViewCount += videoViewCountDTO.getViewsCount();
    }
   List<VideoViewCount> video2ViewCountlist = videoViewsRepository.findAllByVideoIdAndViewsDateBetweeen
            (videoDTO2.getVideoId(),before,now);

    Long video2ViewCount = 0L;
    for(int i=0; i<video2ViewCountlist.size(); i++){
        VideoViewCount viewcount = video2ViewCountlist.get(i);
        VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO();
        videoViewCountDTO.makeEntityToDTO(viewcount);
        video2ViewCount += videoViewCountDTO.getViewsCount();
    }

    if(video1ViewCount>video2ViewCount) return -1;
    else if (video1ViewCount==video2ViewCount)
        return 0;
    else
        return 1;
});
  return videos;

    }

    public List<Channel> compareChannelVisitCount(List<Channel> channels, int days)
    {
        Collections.sort(channels,(channel1, channel2)->{

            ChannelDTO channelDTO1 = new ChannelDTO();
            channelDTO1.makeEntityToDTO(channel1);
            ChannelDTO channelDTO2 = new ChannelDTO();
            channelDTO2.makeEntityToDTO(channel2);

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime before = now.minusDays(days);

            List<ChannelVisitCount> channel1VisitCountlist = channelVisitCountRepository.findAllByChannelIdAndChannelVisitDateBetween
                    (channelDTO1.getChannelId(),before,now);

            Long channel1VisitCount =0L;
            for(int i=0; i<channel1VisitCountlist.size(); i++){
                ChannelVisitCount visitcount = channel1VisitCountlist.get(i);
                ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO();
                channelVisitCountDTO.makeEntityToDTO(visitcount);
                channel1VisitCount += channelVisitCountDTO.getChannelVisitCount();
            }

            List<ChannelVisitCount> channel2VisitCountlist = channelVisitCountRepository.findAllByChannelIdAndChannelVisitDateBetween
                    (channelDTO2.getChannelId(),before,now);

            Long channel2VisitCount =0L;
            for(int i=0; i<channel2VisitCountlist.size(); i++){
                ChannelVisitCount visitcount = channel2VisitCountlist.get(i);
                ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO();
                channelVisitCountDTO.makeEntityToDTO(visitcount);
                channel2VisitCount += channelVisitCountDTO.getChannelVisitCount();
            }

            if(channel1VisitCount>channel2VisitCount) return -1;
            else if (channel1VisitCount==channel2VisitCount)
                return 0;
            else
                return 1;
        });
        return channels;
    }

}
