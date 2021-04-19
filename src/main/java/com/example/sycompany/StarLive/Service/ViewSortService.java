package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.ChannelDTO;
import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewSortService{

    final VideoViewsRepository videoViewsRepository;
    final ChannelVisitCountRepository channelVisitCountRepository;

    static final int SWITCH_SET = -1;
    static final int EQUAL = 0;
    static final int SWITCH_UNSET = 1;


public List<Video> compareVideoViewCount(List<Video> videos, int days) //비디오 조회수 내림차순 정렬
{

    videos.sort((video2, video1) -> {

        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);

        List<VideoViewCount> video1ViewCountList = videoViewsRepository.findByVideoAndViewsDateBetween
                (video1, before, now);

        Long video1ViewCount = 0L;

        for (VideoViewCount videoViewCount : video1ViewCountList) {

            VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO(videoViewCount);

            video1ViewCount += videoViewCountDTO.getViewsCount();
        }

        List<VideoViewCount> video2ViewCountList = videoViewsRepository.findByVideoAndViewsDateBetween
                (video2, before, now);

        Long video2ViewCount = 0L;

        for (VideoViewCount videoViewCount : video2ViewCountList) {

            VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO(videoViewCount);

            video2ViewCount += videoViewCountDTO.getViewsCount();
        }

        if (video1ViewCount > video2ViewCount) return SWITCH_SET;
        else if (video1ViewCount.equals(video2ViewCount))
            return EQUAL;
        else
            return SWITCH_UNSET;
    });
    return videos;

}

    public List<Channel> compareChannelVisitCount(List<Channel> channels, int days)
    {
        channels.sort((channel2, channel1) -> {

            ChannelDTO channelDTO1;
            channelDTO1 = new ChannelDTO(channel1);
            ChannelDTO channelDTO2;
            channelDTO2 = new ChannelDTO(channel2);

            LocalDate now = LocalDate.now();
            LocalDate before = now.minusDays(days);

            List<ChannelVisitCount> channel1VisitCountlist = channelVisitCountRepository.findByChannelAndChannelVisitDateBetween
                    (channel1, before, now);

            Long channel1VisitCount = 0L;
            for (int i = 0; i < channel1VisitCountlist.size(); i++) {
                ChannelVisitCount visitcount = channel1VisitCountlist.get(i);
                ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO(visitcount);
                channel1VisitCount += channelVisitCountDTO.getChannelVisitCount();
            }

            List<ChannelVisitCount> channel2VisitCountlist = channelVisitCountRepository.findByChannelAndChannelVisitDateBetween
                    (channel2, before, now);

            Long channel2VisitCount = 0L;

            for (ChannelVisitCount channelVisitCount : channel2VisitCountlist) {
                ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO(channelVisitCount);
                channel2VisitCount += channelVisitCountDTO.getChannelVisitCount();
            }

            return channel1VisitCount.compareTo(channel2VisitCount);
        });
        return channels;
    }

    // 채널 구독 멤버 순으로 정렬을 해준다.
    public List<Channel> compareChannelMemberCount(List<Channel>channels){
        channels.sort((channel2, channel1) -> {


            ChannelDTO channelDTO1 = new ChannelDTO(channel1);
            ChannelDTO channelDTO2 = new ChannelDTO(channel2);

            if (channelDTO1.getMemberCount() < channelDTO2.getMemberCount()){
                return SWITCH_SET;
            }
            else if (channelDTO1 == channelDTO2) {
                return EQUAL;
            }
            else {
                return SWITCH_UNSET;
            }
        });
        return channels;
    }


    public List<Channel> compareChannelByCreatedAt(List<Channel>channels){

    channels.sort((channel2, channel1) -> {

            ChannelDTO channelDTO1 = new ChannelDTO(channel1);
            ChannelDTO channelDTO2 = new ChannelDTO(channel2);

            LocalDateTime date1 = channelDTO1.getCreatedAt();
            LocalDateTime date2 = channelDTO2.getCreatedAt();

            if (date1.isBefore(date2)) {
                return SWITCH_SET;
            } else if (date1.isEqual(date2)) {
                return EQUAL;
            } else {
                return SWITCH_UNSET;
            }
        });

    return channels;
    }

    public List<Channel> compareChannelByABC(List<Channel>channels){

    channels.sort((channel2, channel1) -> {

            ChannelDTO channelDTO1;
            channelDTO1 = new ChannelDTO(channel1);

            ChannelDTO channelDTO2;
            channelDTO2 = new ChannelDTO(channel2);

            String channelName1 = channelDTO1.getChannelName();
            String channelName2 = channelDTO2.getChannelName();

            return Integer.compare(0, channelName1.compareTo(channelName2));


        });

        return channels;
    }

}
