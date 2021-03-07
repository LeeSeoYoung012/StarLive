package com.example.sycompany.StarLive.Example;

import com.example.sycompany.StarLive.DTO.*;
import com.example.sycompany.StarLive.Entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Example {


    public ChannelDTO ChannelExample(){
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setChannelId(20L);
        channelDTO.setMemberCount(10000000L);
        channelDTO.setChannelName("BTS");
        channelDTO.setChannelPicture("bts.jpg");
        channelDTO.setLikesCount(20000000L);
        channelDTO.setCreatedAt(LocalDateTime.of(2019, 11, 12,12, 32,22,3333));
        return channelDTO;
    }

    public VideoFileDTO VideoFileExample(){
        VideoFileDTO videoFileDTO = new VideoFileDTO();
        videoFileDTO.setFileId(3L);
        videoFileDTO.setFileSize(120L);
        videoFileDTO.setFileUrl("ttt ");
        return videoFileDTO;
    }

    public VideoDTO VideoExample(){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setVideoId(10L);
        videoDTO.setChannel(new Channel(ChannelExample()));
        videoDTO.setTitle("This is new Video");
        videoDTO.setCreatedAt(LocalDateTime.now());
        videoDTO.setLikeCount(20000L);
        videoDTO.setVideoFile(new VideoFile(VideoFileExample()));
        return videoDTO;
    }



    public List<VideoFile> VideoFileListExample(){
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



    public List<Video> VideoListExample(){

        int total = 5;
        Long[] id_array = {1L,2L,3L,4L,5L};
        String[] title_array = {"a","b","c","d","e"};
        String[] dateTimeString_array = {"2020-08-04T10:11:30","2020-08-04T11:20:30","2020-08-05T09:11:30","2020-08-06T07:11:30","2020-08-07T10:11:30"};
        List<Channel> channelList = ChannelListExample();
        Long[] likesCount_array = {7595812L,601123L,1234567L,3939393L,9090997L};
        List<VideoFile> videoFile = VideoFileListExample();
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


    public List<VideoViewCount> VideoViewCountListExample(Video videoParam, int days, Long initViewCount){
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


    public List<Channel> ChannelListExample(){

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


    public List<ChannelVisitCount> ChannelVisitCountListExample(Channel channelParam, int days, Long visitCount){

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


    public ChannelVisitCountDTO ChannelVisitCountDTOExample() {

        Channel channel = new Channel(ChannelExample());
        ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO();
        channelVisitCountDTO.setChannelVisitId(1L);
        channelVisitCountDTO.setChannelVisitCount(200000L);
        channelVisitCountDTO.setChannel(channel);
        channelVisitCountDTO.setChannelVisitDate(LocalDate.now());
        return channelVisitCountDTO;

    }


}
