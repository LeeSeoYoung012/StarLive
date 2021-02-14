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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
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

    LocalDate now = LocalDate.now();
    LocalDate before = now.minusDays(days);

    List<VideoViewCount> video1ViewCountlist = videoViewsRepository.findByVideoAndViewsDateBetween
            (new Video(videoDTO1),before,now);

    Long video1ViewCount =0L;
    for(int i=0; i<video1ViewCountlist.size(); i++){
        VideoViewCount viewcount = video1ViewCountlist.get(i);
        VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO(viewcount);

        video1ViewCount += videoViewCountDTO.getViewsCount();
    }
   List<VideoViewCount> video2ViewCountlist = videoViewsRepository.findByVideoAndViewsDateBetween
            (new Video(videoDTO2),before,now);

    Long video2ViewCount = 0L;
    for(int i=0; i<video2ViewCountlist.size(); i++){
        VideoViewCount viewcount = video2ViewCountlist.get(i);
        VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO(viewcount);
    ;
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

            ChannelDTO channelDTO1;
            channelDTO1=channel1.makeEntityToDTO(channel1);
            ChannelDTO channelDTO2;
            channelDTO2= channel2.makeEntityToDTO(channel2);

            LocalDate now = LocalDate.now();
            LocalDate before = now.minusDays(days);

            List<ChannelVisitCount> channel1VisitCountlist = channelVisitCountRepository.findAllByChannelAndChannelVisitDateBetween
                    (new Channel(channelDTO1),before,now);

            Long channel1VisitCount =0L;
            for(int i=0; i<channel1VisitCountlist.size(); i++){
                ChannelVisitCount visitcount = channel1VisitCountlist.get(i);
                ChannelVisitCountDTO channelVisitCountDTO;
                channelVisitCountDTO= visitcount.makeEntityToDTO(visitcount);
                channel1VisitCount += channelVisitCountDTO.getChannelVisitCount();
            }

            List<ChannelVisitCount> channel2VisitCountlist = channelVisitCountRepository.findAllByChannelAndChannelVisitDateBetween
                    (new Channel(channelDTO2),before,now);

            Long channel2VisitCount =0L;
            for(int i=0; i<channel2VisitCountlist.size(); i++){
                ChannelVisitCount visitcount = channel2VisitCountlist.get(i);
                ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO();
                channelVisitCountDTO= visitcount.makeEntityToDTO(visitcount);
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

    // 채널 구독 멤버 순으로 정렬을 해준다.
    public List<Channel> compareChannelMemberCount(List<Channel>channels){
        Collections.sort(channels,(channel1, channel2)->{


            ChannelDTO channelDTO1 = new ChannelDTO();
            channelDTO1 = channel1.makeEntityToDTO(channel1);
            ChannelDTO channelDTO2 = new ChannelDTO();
            channelDTO2 = channel2.makeEntityToDTO(channel2);

            if(channelDTO1.getMemberCount()>channelDTO2.getMemberCount()) return -1;
            else if(channelDTO1==channelDTO2){
                return 0;
            }
            else
                return 1;

        });
        return channels;
    }


    public List<Channel> compareChannelByCreatedAt(List<Channel>channels){
        Collections.sort(channels,(channel1, channel2)->{
            ChannelDTO channelDTO1 = new ChannelDTO();
            channelDTO1= channel1.makeEntityToDTO(channel1);
            ChannelDTO channelDTO2 = new ChannelDTO();
            channelDTO2 = channel2.makeEntityToDTO(channel2);
            LocalDateTime date1 = channelDTO1.getCreatedAt();
            LocalDateTime date2 = channelDTO2.getCreatedAt();
            if(date1.isBefore(date2)){
                return -1;
            }
            else if(date1.isEqual(date2)){
                return 0;
            }
            else{
                return 1;
            }
        });
        return channels;
    }

    public List<Channel> compareChannelByABC(List<Channel>channels){
        Collections.sort(channels,(channel1, channel2)->{

            ChannelDTO channelDTO1;
            channelDTO1=channel1.makeEntityToDTO(channel1);
            ChannelDTO channelDTO2;
            channelDTO2=channel2.makeEntityToDTO(channel2);

            String channelName1 = channelDTO1.getChannelName();
            String channelName2 = channelDTO2.getChannelName();

            if(channelName1.compareTo(channelName2)<0){
                return -1;
            }
            else if(channelName1.compareTo(channelName2)>0){
                return 1;
            }
            else{
                return 0;
            }


        });

        return channels;
    }

}
