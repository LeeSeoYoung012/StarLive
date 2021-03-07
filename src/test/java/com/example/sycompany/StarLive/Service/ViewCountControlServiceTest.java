package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.*;
import com.example.sycompany.StarLive.Entity.*;
import com.example.sycompany.StarLive.Repository.ChannelVisitCountRepository;
import com.example.sycompany.StarLive.Repository.VideoViewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ViewCountControlServiceTest {


    @Mock
    VideoViewsRepository videoViewsRepository;

    @Mock
    ChannelVisitCountRepository channelVisitCountRepository;

    @Mock
    VideoViewCount videoViewCount;

    @InjectMocks
    ViewCountControlService viewCountControlService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach  //테스트 전 선작업
    public void setUp(){
        //LocalDate now = LocalDate.now();
        //Mockito.when(channelVisitCountRepository.findByChannelVisitDate(now)).thenReturn( new ChannelVisitCount(getChannelVisitCountExample()));
    }

    private List<Video> getVideoListExample(Video video1, Video video2){
        List<Video> videoList = new ArrayList<>();
        videoList.add(video1);
        videoList.add(video2);
        return videoList;
    }

    private VideoDTO getAnotherVideoExample(){
        VideoDTO videoDTO = getVideoExample();
        videoDTO.setVideoId(11L);
        videoDTO.setTitle("another new video");
        videoDTO.setLikeCount(30000L);
        return videoDTO;
    }


    private VideoDTO getVideoExample(){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setVideoId(10L);
        videoDTO.setChannel(new Channel(getChannelExample()));
        videoDTO.setTitle("This is new Video");
        videoDTO.setCreatedAt(LocalDateTime.now());
        videoDTO.setLikeCount(20000L);
        videoDTO.setVideoFile(new VideoFile(getVideoFile()));
        return videoDTO;
    }

    private VideoFileDTO getVideoFile(){
        VideoFileDTO videoFileDTO = new VideoFileDTO();
        videoFileDTO.setFileId(3L);
        videoFileDTO.setFileSize(120L);
        videoFileDTO.setFileUrl("ttt ");
        return videoFileDTO;
    }

    private VideoViewCountDTO getVideoViewCountDTOExample(){

        VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO();
        videoViewCountDTO.setViewId(1L);
        videoViewCountDTO.setViewsCount(30000L);
        videoViewCountDTO.setViewsDate(LocalDate.now());
        videoViewCountDTO.setVideo(new Video(getVideoExample()));
        return videoViewCountDTO;

    }

    private VideoViewCount getVideoViewCountExample(){
        VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO();
        videoViewCountDTO.setViewId(1L);
        videoViewCountDTO.setViewsCount(30000L);
        videoViewCountDTO.setViewsDate(LocalDate.now());
        videoViewCountDTO.setVideo(new Video(getVideoExample()));
        return new VideoViewCount(videoViewCountDTO);
    }

    private List<VideoViewCount> getVideoViewCountListExample(VideoDTO videoDTO){
        Video video = new Video(videoDTO);
        List<VideoViewCount> list = new ArrayList<>();
        VideoViewCountDTO videoViewCountDTO = new VideoViewCountDTO();
        videoViewCountDTO.setVideo(video);
        videoViewCountDTO.setViewsDate(LocalDate.of(2020,1,1));
        videoViewCountDTO.setViewId(1L);
        videoViewCountDTO.setViewsCount(160000L);
        list.add(new VideoViewCount(videoViewCountDTO));

        videoViewCountDTO.setVideo(video);
        videoViewCountDTO.setViewsDate(LocalDate.of(2020,1,2));
        videoViewCountDTO.setViewId(1L);
        videoViewCountDTO.setViewsCount(190000L);
        list.add(new VideoViewCount(videoViewCountDTO));

        videoViewCountDTO.setVideo(video);
        videoViewCountDTO.setViewsDate(LocalDate.of(2020,1,3));
        videoViewCountDTO.setViewId(1L);
        videoViewCountDTO.setViewsCount(200000L);
        list.add(new VideoViewCount(videoViewCountDTO));

        videoViewCountDTO.setVideo(video);
        videoViewCountDTO.setViewsDate(LocalDate.of(2020,1,4));
        videoViewCountDTO.setViewId(1L);
        videoViewCountDTO.setViewsCount(160000L);
        list.add(new VideoViewCount(videoViewCountDTO));

        return list;
    }

    private ChannelDTO getChannelExample(){
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setChannelId(20L);
        channelDTO.setMemberCount(10000000L);
        channelDTO.setChannelName("BTS");
        channelDTO.setChannelPicture("bts.jpg");
        channelDTO.setLikesCount(20000000L);
        channelDTO.setCreatedAt(LocalDateTime.of(2019, 11, 12,12, 32,22,3333));
        return channelDTO;
    }

    private ChannelVisitCountDTO getChannelVisitCountDTOExample() {

        Channel channel = new Channel(getChannelExample());
        ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO();
        channelVisitCountDTO.setChannelVisitId(1L);
        channelVisitCountDTO.setChannelVisitCount(200000L);
        channelVisitCountDTO.setChannel(channel);
        channelVisitCountDTO.setChannelVisitDate(LocalDate.now());
        return channelVisitCountDTO;

    }

    private List<ChannelVisitCount> getChannelVisitCountListExample(){
        Channel channel = new Channel(getChannelExample());
        List <ChannelVisitCount> list = new ArrayList<>();

        ChannelVisitCountDTO channelVisitCountDTO = new ChannelVisitCountDTO();
        channelVisitCountDTO.setChannelVisitId(1L);
        channelVisitCountDTO.setChannelVisitCount(200000L);
        channelVisitCountDTO.setChannel(channel);
        channelVisitCountDTO.setChannelVisitDate(LocalDate.of(2020,1,1));
        list.add(new ChannelVisitCount(channelVisitCountDTO));

        channelVisitCountDTO.setChannelVisitId(2L);
        channelVisitCountDTO.setChannelVisitCount(350000L);
        channelVisitCountDTO.setChannel(channel);
        channelVisitCountDTO.setChannelVisitDate(LocalDate.of(2020,1,2));
        list.add(new ChannelVisitCount(channelVisitCountDTO));

        channelVisitCountDTO.setChannelVisitId(3L);
        channelVisitCountDTO.setChannelVisitCount(340000L);
        channelVisitCountDTO.setChannel(channel);
        channelVisitCountDTO.setChannelVisitDate(LocalDate.of(2020,1,3));
        list.add(new ChannelVisitCount(channelVisitCountDTO));

        channelVisitCountDTO.setChannelVisitId(4L);
        channelVisitCountDTO.setChannelVisitCount(300000L);
        channelVisitCountDTO.setChannel(channel);
        channelVisitCountDTO.setChannelVisitDate(LocalDate.of(2020,1,4));
        list.add(new ChannelVisitCount(channelVisitCountDTO));

        return list;

    }



    //채널 클릭시 현재 날짜의 조회수를 증가하는지 확인
    @Test
    void TestChannelVisitCountIncrease() {

        LocalDate now = LocalDate.now();
        Mockito.when(channelVisitCountRepository.findByChannelVisitDate(now)).thenReturn( new ChannelVisitCount(getChannelVisitCountDTOExample()));
        Channel channel = new Channel(getChannelExample());
        ChannelVisitCountDTO nowvisitDTO;
        nowvisitDTO= viewCountControlService.channelVisitCountIncrease(channel);
        if(nowvisitDTO !=null) {
            assertEquals(nowvisitDTO.getChannelVisitId(),1L);
            assertEquals(nowvisitDTO.getChannelVisitCount(), 200001L);
            assertEquals(nowvisitDTO.getChannelVisitDate(), now);
            assertEquals(nowvisitDTO.getChannel().getChannelId(), channel.getChannelId());
        }
    }

    //채널 클릭시 현재 날짜가 없으면 생성하서 조회수 1로 증가하는지 확인
    @Test
    void TestChannelVisitCountIncreaseIfNull() {
        LocalDate now = LocalDate.now();
        Mockito.when(channelVisitCountRepository.findByChannelVisitDate(now)).thenReturn( null);
        Channel channel = new Channel(getChannelExample());
        ChannelVisitCountDTO nowvisitDTO = viewCountControlService.channelVisitCountIncrease(channel);
        assertEquals(nowvisitDTO.getChannelVisitId(),1L);
        assertEquals(nowvisitDTO.getChannelVisitCount(),1L);
        assertEquals(nowvisitDTO.getChannelVisitDate(),now);
        assertEquals(nowvisitDTO.getChannel().getChannelId(),channel.getChannelId());
    }


    //해당 채널의 총 조회수가 올바르게 나오는지 확인
    @Test
    void TestChannelVisitCountTotal() {
        Channel channel = new Channel(getChannelExample());
        Mockito.when(channelVisitCountRepository.findByChannel(channel)).thenReturn(getChannelVisitCountListExample());
        Long totalCount = viewCountControlService.channelVisitCountTotal(channel);
        assertEquals(totalCount,1190000);
    }


    @Test
    void updateChannelVisitCount(){
        ChannelVisitCountDTO channelVisitCountDTO = getChannelVisitCountDTOExample();
        Long id = channelVisitCountDTO.getChannelVisitId();
        ChannelVisitCount channelVisitCount= new ChannelVisitCount(getChannelVisitCountDTOExample());

        Mockito.when(channelVisitCountRepository.findById(id)).thenReturn(java.util.Optional.of(channelVisitCount));
        Mockito.when(channelVisitCount.update(channelVisitCountDTO)).thenReturn(channelVisitCountDTO);
        //Long updateId = updateChannelVisitCount(channelVisitCountDTO);
    }


    @Test
    void videoViewCountIncrease() {
        LocalDate now = LocalDate.now();
        Video video = new Video(getVideoExample());
        Mockito.when(videoViewsRepository.findByViewsDate(now)).thenReturn(new VideoViewCount(getVideoViewCountDTOExample()));
        VideoViewCountDTO videoViewCountDTO= viewCountControlService.videoViewCountIncrease(video);
        assertEquals(videoViewCountDTO.getVideo().getVideoId(),video.getVideoId());
        assertEquals(videoViewCountDTO.getViewsCount(),30001);
        assertEquals(videoViewCountDTO.getViewId(),1L);
        assertEquals(videoViewCountDTO.getViewsDate(),LocalDate.now());
    }

    @Test
    void videoViewCountIncreaseIfNull() {
        LocalDate now = LocalDate.now();
        Video video = new Video(getVideoExample());
        Mockito.when(videoViewsRepository.findByViewsDate(now)).thenReturn(null);
        VideoViewCountDTO videoViewCountDTO= viewCountControlService.videoViewCountIncrease(video);
        assertEquals(videoViewCountDTO.getVideo().getVideoId(),video.getVideoId());
        assertEquals(videoViewCountDTO.getViewsCount(),1L);
        assertEquals(videoViewCountDTO.getViewsDate(),LocalDate.now());
    }

    @Test
    void getReturnOfUpdateVideoViewCountIfNull() {
       VideoViewCountDTO videoViewCountDTO = getVideoViewCountDTOExample();
       videoViewCountDTO.setViewId(null);
        VideoViewCount videoViewCount = new VideoViewCount(videoViewCountDTO);
        Mockito.when(videoViewsRepository.save(videoViewCount)).thenReturn(videoViewCount);
        Long res = viewCountControlService.updateVideoViewCount(videoViewCountDTO);
        assertEquals(res,-1L);
    }

    @Test
    void getReturnOfUpdateVideoViewCount(){
        VideoViewCountDTO videoViewCountDTO = getVideoViewCountDTOExample();
        Long id = videoViewCountDTO.getViewId();
        Mockito.when(videoViewsRepository.findById(id)).thenReturn(null); 
        Mockito.when(videoViewCount.update(videoViewCountDTO)).thenReturn(1);
        Long res = viewCountControlService.updateVideoViewCount(videoViewCountDTO);
        assertEquals(res,videoViewCountDTO.getViewId());
    }

    @Test
    void videoViewCountTotal() {
        Video video =  new Video(getVideoExample());
        Mockito.when(videoViewsRepository.findByVideo(video)).thenReturn(getVideoViewCountListExample(getVideoExample()));
        Long totalCount = 0L;
        totalCount = viewCountControlService.videoViewCountTotal(video);
        assertEquals(totalCount,710000);
    }

    @Test
    void severalVideosViewCountTotal() {
        VideoDTO videoDTO1 = getVideoExample();
        VideoDTO videoDTO2 = getAnotherVideoExample();
        Video video1 = new Video(videoDTO1 );
        Video video2 = new Video(videoDTO2);
        Mockito.when(videoViewsRepository.findByVideo(video1)).thenReturn(getVideoViewCountListExample(videoDTO1));
        Mockito.when(videoViewsRepository.findByVideo(video2)).thenReturn(getVideoViewCountListExample(videoDTO2));
        Long res = viewCountControlService.severalVideosViewCountTotal(getVideoListExample(video1,video2));
       assertEquals(res,1420000 );
    }
}
