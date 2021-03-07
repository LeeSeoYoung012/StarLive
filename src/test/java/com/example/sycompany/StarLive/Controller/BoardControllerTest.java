package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.DTO.CommentsDTO;
import com.example.sycompany.StarLive.DTO.UserDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Example.BoardExample;
import com.example.sycompany.StarLive.Example.Example;
import com.example.sycompany.StarLive.Repository.ChannelRepository;
import com.example.sycompany.StarLive.Repository.CommentsRepository;
import com.example.sycompany.StarLive.Service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {


    @InjectMocks
    BoardController boardController;

    @MockBean
    private CommentsRepository commentsRepository;

    @MockBean
    private ChannelRepository channelRepository;

    @MockBean
    BoardService boardService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;


    @BeforeEach  //테스트 전 선작업
    private void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();

    }




    @Test
    void getFanPostList() {

        Example example = new Example();
        Channel channel = new Channel(example.ChannelExample());
        Long channelId = channel.getChannelId();
        Mockito.when(channelRepository.findByChannelId(channelId)).thenReturn(channel);

    }

    @Test
    void getStarPostList() {
    }

    @Test
    void getCommentsList() {
    }

    @Test
    void postComments() throws Exception {

        BoardExample boardExample = new BoardExample();
        CommentsDTO commentsDTO = boardExample.CommentsBasicExample();

        Comments comments = new Comments(commentsDTO);

        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(commentsDTO);



        Mockito.when(commentsRepository.save(comments)).thenReturn(comments);
        MvcResult result =
                mockMvc.perform(
                        MockMvcRequestBuilders.post("/comments")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                        .andExpect( MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        String resContent = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Comments resComments = objectMapper.readValue(resContent, Comments.class);

        assertEquals(resComments.getCommentsId(),comments.getCommentsId());
        assertEquals(resComments.getChannel(),comments.getChannel());
        assertEquals(resComments.getContent(),comments.getContent());
        assertEquals(resComments.getStarFan(),comments.getStarFan());
        assertEquals(resComments.getLikesCount(), comments.getLikesCount());
        assertEquals(resComments.getUserNum(), comments.getUserNum());

    }

    @Test
    void putComments() {
    }

    @Test
    void deleteComments() {
    }
}