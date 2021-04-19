package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import com.example.sycompany.StarLive.DTO.UserDTO;
import com.example.sycompany.StarLive.DTO.VideoDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.ChannelVisitCount;
import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Example.Example;
import com.example.sycompany.StarLive.Repository.*;
import com.example.sycompany.StarLive.Service.ViewCountControlService;
import com.example.sycompany.StarLive.Service.ViewSortService;
import com.example.sycompany.StarLive.Service.ViewSortServiceTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MainPageControllerTest<ViewSortServiceTest> {

    @InjectMocks
    MainPageController mainPageController;

    @Mock
    VideoRepository videoRepository;

    @Mock
    ChannelRepository channelRepository;

    @Mock
    UserSubscribeListRepsitory userSubscribeListRepsitory;

    @Mock
    ViewSortService viewSortService;

    @Mock
    VideoViewsRepository videoViewsRepository;

    @Mock
    ViewCountControlService viewCountControlService;

    @Mock
    ChannelVisitCountRepository channelVisitCountRepository;


    ViewSortServiceTest viewSortServiceTest;


    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach  //테스트 전 선작업
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
       mockMvc = MockMvcBuilders.standaloneSetup(mainPageController).build();

    }

    public void compareVideoViewCountMock(List<Video>videos, int days ){ //모든 비디오에 대해서 mock

        Example example = new Example();

        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);
        Long viewCount = 10000L;

        int videoListSize = videos.size();
        for(int i=0; i<videoListSize; i++){
            Video video =  videos.get(i);
            Mockito.when(videoViewsRepository.findByVideoAndViewsDateBetween(video,before,now)).thenReturn(example.VideoViewCountListExample(video,days,viewCount));
            viewCount++;
        }

    }

    public void compareChannelViewCountMock(List<Channel>channels, int days){ //모든 채널에 대해서 mock

        Example example = new Example();

        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(days);
        Long viewCount = 10000L;

        int channelListSize = channels.size();
        for(int i=0; i<channelListSize; i++){
            Channel channel = channels.get(i);
            Mockito.when(channelVisitCountRepository.findByChannelAndChannelVisitDateBetween(channel,before,now)).thenReturn(example.ChannelVisitCountListExample(channel,days,viewCount));
            viewCount++;
        }

    }



    @Test
    void checkGetVideosOrderByViewCount() throws Exception {

        int days =1;

        Example example = new Example();
        List<Video> videoList = example.VideoListExample();
        Mockito.when(videoRepository.findAll()).thenReturn(videoList);

        compareVideoViewCountMock(videoList,days);

        Mockito.when(viewSortService.compareVideoViewCount(videoList,days)).thenReturn(videoList);
        int videoListSize = videoList.size();

        MvcResult result =
                (MvcResult) mockMvc.perform(MockMvcRequestBuilders.get("/home/chart/video/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect( status().isOk())
                        .andDo(print())
                        .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Video> sortedVideos= objectMapper.readValue(content, new TypeReference<List<Video>>(){});

        for(int i=0; i<sortedVideos.size(); i++){
            assertEquals(sortedVideos.get(i).getVideoId(),i);
        }
    }


    @Test
    void checkGetChannelsOrderByChannelCount() throws Exception {

        int days =1;
        Example example = new Example();
        List<Channel>channelList = example.ChannelListExample();
        Mockito.when(channelRepository.findAll()).thenReturn(channelList);

        compareChannelViewCountMock(channelList,days);

        int channelListSize = channelList.size();

        MvcResult result  =
                (MvcResult) mockMvc.perform(MockMvcRequestBuilders.get("/home/chart/channel/{days}")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect( status().isOk())
                        .andDo(print())
                        .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Channel> sortedChannels = objectMapper.readValue(content, new TypeReference<List<Channel>>(){});

        for(int i=0; i<sortedChannels.size(); i++){
            assertEquals(sortedChannels.get(i).getChannelId(),channelListSize-i);
        }

    }

    @Test
    void TestPutChannelVisitCountReturn() throws Exception {

        Example example = new Example();
        Channel channel = new Channel(example.ChannelExample());
        ChannelVisitCountDTO channelVisitCountDTO = example.ChannelVisitCountDTOExample();

        Mockito.when(channelRepository.findByChannelId(channel.getChannelId())).thenReturn(channel);
        Mockito.when( viewCountControlService.channelVisitCountIncrease(channel)).thenReturn(channelVisitCountDTO);
        Mockito.when(viewCountControlService.updateChannelVisitCount(channelVisitCountDTO)).thenReturn(channelVisitCountDTO.getChannelVisitId());

        Long channelId = channel.getChannelId();
        MvcResult result  =
                (MvcResult) mockMvc.perform(MockMvcRequestBuilders.get("/home/chart/channel/{channelId}")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect( status().isOk())
                        .andDo(print())
                        .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,channelId.toString());

    }


}