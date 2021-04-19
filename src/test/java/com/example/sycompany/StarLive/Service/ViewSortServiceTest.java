package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.*;
import com.example.sycompany.StarLive.Entity.*;
import com.example.sycompany.StarLive.Example.Example;
import com.example.sycompany.StarLive.Repository.ChannelVisitCountRepository;
import com.example.sycompany.StarLive.Repository.VideoViewsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public
class ViewSortServiceTest {


    @Mock
    VideoViewsRepository videoViewsRepository;

    @Mock
    ChannelVisitCountRepository channelVisitCountRepository;

    @InjectMocks
    ViewSortService viewSortService;

    //Example example;

    private List<Channel> getChannelList(){

        int total = 5;

        Long[] channel_id_array = {1L,2L,3L,4L,5L};
        String[] channel_image_array = {"a.jpg","b.jpg","c.jpg","d.jpg","e.jpg"};
        String[] channel_name_array = {"Bts","BigBang","BlackPink","IOI","Girls Generation"};
        Long[] channel_likesCount_array = {130300000000L,1300000000L,90300000000L,50000000L,100000000L};
        Long [] channel_memberCount_array = {25493878L,6722222L,11962340L,5777777L,6000000L};
        String [] channel_createdAt_stringArray= {"2013-08-04T10:11:30","2012-07-14T11:15:42","2017-03-05T17:11:30","2019-04-05T17:11:30","2012-05-05T17:11:30"};

        List<Channel>channelList = new ArrayList<>();
        for(int i=0; i<total; i++){
            ChannelDTO channelDTO = new ChannelDTO();
            channelDTO.setChannelId(channel_id_array[i]);
            channelDTO.setChannelPicture(channel_image_array[i]);
            channelDTO.setChannelName(channel_name_array[i]);
            channelDTO.setLikesCount(channel_likesCount_array[i]);
            channelDTO.setMemberCount(channel_memberCount_array[i]);
            channelDTO.setCreatedAt(LocalDateTime.parse(channel_createdAt_stringArray[i]));
            channelList.add(new Channel(channelDTO));
        }

        return channelList;


    }

    private List<VideoFile> getVideoFileList(){

        int total = 5;

        Long[] videoFile_id_array = {2L,3L,4L,5L,6L};
        Long[] videoSize_array = {120L,250L,140L,299L,381L};
        String[] videoUrl_array = {"a","b","c","d","e"};
        VideoFile videoFile;

        List<VideoFile>videoFileList= new ArrayList<>();
        for(int i=0; i<total; i++){
            VideoFileDTO videoFileDTO = new VideoFileDTO();
            videoFileDTO.setFileUrl(videoUrl_array[i]);
            videoFileDTO.setFileId(videoFile_id_array[i]);
            videoFileDTO.setFileSize(videoSize_array[i]);
            videoFileList.add(new VideoFile(videoFileDTO));
        }
        return videoFileList;
    }



    private List<Video> getVideoList(){

        int total = 5;
        Long[] id_array = {1L,2L,3L,4L,5L};
        String[] title_array = {"a","b","c","d","e"};
        String[] dateTimeString_array = {"2020-08-04T10:11:30","2020-08-04T11:20:30","2020-08-05T09:11:30","2020-08-06T07:11:30","2020-08-07T10:11:30"};
        List<Channel> channelList = getChannelList();
        Long[] likesCount_array = {7595812L,601123L,1234567L,3939393L,9090997L};
        List<VideoFile> videoFile = getVideoFileList();
        Long[] chattingId_array = {1L,2L,3L,4L,5L};
        List<Video>videoList = new ArrayList<>();

        for(int i=0; i<total; i++){
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setVideoId(id_array[i]);
            videoDTO.setTitle(title_array[i]);
            videoDTO.setCreatedAt(LocalDateTime.parse(dateTimeString_array[i]));
            videoDTO.setChannel(channelList.get(i));
            videoDTO.setLikeCount(likesCount_array[i]);
            videoDTO.setChattingId(chattingId_array[i]);
            videoDTO.setVideoFile(videoFile.get(i));
            videoList.add(new Video(videoDTO));
        }

        return videoList;


    }


    private List<VideoViewCount> getVideoViewCountList(Video videoParam, int days, Long initViewCount){
        Long id = 32L;

        List<VideoViewCount> videoViewCountList = new ArrayList<>();

        LocalDate localDate = LocalDate.now();

        for (int j = 0; j < days; j++) {
            VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO();
            videoViewCountDTO.setViewId(id);
            videoViewCountDTO.setViewsCount(initViewCount);
            videoViewCountDTO.setViewsDate(localDate);
            videoViewCountDTO.setVideo(videoParam);

            videoViewCountList.add(new VideoViewCount(videoViewCountDTO));

            initViewCount--;
            id--;
            localDate = localDate.minusDays(1);
        }


        return videoViewCountList;
    }


    private List<ChannelVisitCount> getChannelVisitCountList(Channel channelParam, int days, Long visitCount){

        List<ChannelVisitCount> channelVisitCountList = new ArrayList<>();

        Long id = 32L;

        LocalDate localDate = LocalDate.now();
        for(int i=0; i<days; i++){
            ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO();
            channelVisitCountDTO.setChannelVisitDate(localDate);
            channelVisitCountDTO.setChannelVisitId(id);
            channelVisitCountDTO.setChannel(channelParam);
            channelVisitCountDTO.setChannelVisitCount(visitCount);
            visitCount--;
            id--;
            localDate = localDate.minusDays(1);
            channelVisitCountList.add(new ChannelVisitCount(channelVisitCountDTO));
        }
        return channelVisitCountList;
    }


    public void compareVideoViewCountMock(List<Video>videos, int days ){
        Example example = new Example();

        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);

        Long viewCount = 10000L;

        for (Video video : videos) {
            Mockito.when(videoViewsRepository.findByVideoAndViewsDateBetween(video, before, now)).thenReturn(example.VideoViewCountListExample(video, days, viewCount));
            viewCount++;
        }
    }



    @Test
    void TestCompareDailyVideoViewCount() {

        int days =1;

        Example example = new Example();
        List<Video> videos = example.VideoListExample();
        compareVideoViewCountMock(videos,days);

        List<Video> sortedVideos = viewSortService.compareVideoViewCount(videos,days);

        int videoListSize = videos.size();
        for(int i=0; i<sortedVideos.size(); i++){
           assertEquals(sortedVideos.get(i).getVideoId(),videoListSize-i);
        }

    }

    @Test
    void TestCompareWeeklyVideoViewCount() {

        int days = 7;
        Example example = new Example();
        List<Video> videos = example.VideoListExample(); //getVideoList();
     //   List<VideoViewCount> videoViewCounts;
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);
        Long viewCount = 10000L;
        int videoListSize = videos.size();

        for (Video video : videos) {
            Mockito.when(videoViewsRepository.findByVideoAndViewsDateBetween(video, before, now)).thenReturn(example.VideoViewCountListExample(video, days, viewCount));
            viewCount++;
        }
        List<Video> sortedVideos = viewSortService.compareVideoViewCount(videos,days);

        for(int i=0; i<sortedVideos.size(); i++){
            assertEquals(sortedVideos.get(i).getVideoId(),videoListSize-i);
        }
    }

    @Test
    void TestCompareMonthlyVideoViewCount() {

        int days = 30;

        Example example = new Example();
        List<Video> videos = example.VideoListExample();
       // List<VideoViewCount> videoViewCounts;
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);
        Long viewCount = 10000L;
        int videoListSize = videos.size();

        for (Video video : videos) {
            Mockito.when(videoViewsRepository.findByVideoAndViewsDateBetween(video, before, now)).thenReturn(example.VideoViewCountListExample(video, days, viewCount));
            viewCount++;
        }
        List<Video> sortedVideos = viewSortService.compareVideoViewCount(videos,days);

        for(int i=0; i<sortedVideos.size(); i++){
            assertEquals(sortedVideos.get(i).getVideoId(),videoListSize-i);
        }
    }

    @Test
    void TestCompareDailyChannelVisitCount() {

        int days =1;
        List<Channel> channels = getChannelList();
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);
        Long visitCount = 10000L;
        int channelListSize = channels.size();

        for (Channel channel : channels) {
            Mockito.when(channelVisitCountRepository.findByChannelAndChannelVisitDateBetween(channel, before, now)).thenReturn(getChannelVisitCountList(channel, days, visitCount));
            visitCount++;
        }
        List<Channel> sortedChannels = viewSortService.compareChannelVisitCount(channels,days);

        for(int i=0; i<sortedChannels.size(); i++){
            assertEquals(sortedChannels.get(i).getChannelId(),channelListSize-i);
        }
    }

    @Test
    void TestCompareWeeklyChannelVisitCount() {

        int days =7;
        List<Channel> channels = getChannelList();
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);
        Long visitCount = 10000L;
        int channelListSize = channels.size();

        for (Channel channel : channels) {
            Mockito.when(channelVisitCountRepository.findByChannelAndChannelVisitDateBetween(channel, before, now)).thenReturn(getChannelVisitCountList(channel, days, visitCount));
            visitCount++;
        }
        List<Channel> sortedChannels = viewSortService.compareChannelVisitCount(channels,days);

        for(int i=0; i<sortedChannels.size(); i++){
            assertEquals(sortedChannels.get(i).getChannelId(),channelListSize-i);
        }
    }

    @Test
    void TestCompareMonthlyChannelVisitCount() {

        int days =30;
        List<Channel> channels = getChannelList();
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);
        Long visitCount = 10000L;
        int channelListSize = channels.size();

        for (Channel channel : channels) {
            Mockito.when(channelVisitCountRepository.findByChannelAndChannelVisitDateBetween(channel, before, now)).thenReturn(getChannelVisitCountList(channel, days, visitCount));
            visitCount++;
        }
        List<Channel> sortedChannels = viewSortService.compareChannelVisitCount(channels,days);

        for(int i=0; i<sortedChannels.size(); i++){
            assertEquals(sortedChannels.get(i).getChannelId(),channelListSize-i);
        }
    }

    @Test
    void TestCompareChannelMemberCount() {
        List<Channel> channels = getChannelList();
        List<Channel> sortedChannels = viewSortService.compareChannelMemberCount(channels);
        int[] right_id_order={1,3,2,5,4};
        for(int i=0; i<channels.size(); i++){
           assertEquals( sortedChannels.get(i).getChannelId(),right_id_order[i]);
        }
    }

    @Test
    void TestCompareChannelByCreatedAt() {
        List<Channel> channels = getChannelList();
        List<Channel> sortedChannels = viewSortService.compareChannelByCreatedAt(channels);
        int[] right_id_order={4,3,1,2,5};
        for(int i=0; i<channels.size(); i++){
            assertEquals( sortedChannels.get(i).getChannelId(),right_id_order[i]);
        }
    }

    @Test
    void TestCompareChannelByABC() {
        List<Channel> channels = getChannelList();
        List<Channel> sortedChannels = viewSortService.compareChannelByABC(channels);
        int[] right_id_order={2,3,1,5,4};
        for(int i=0; i<channels.size(); i++){
            assertEquals( sortedChannels.get(i).getChannelId(),right_id_order[i]);
        }
    }
}