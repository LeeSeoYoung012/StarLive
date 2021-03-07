package com.example.sycompany.StarLive.Example;

import com.example.sycompany.StarLive.DTO.CommentsDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Entity.User;

public class BoardExample {



    public CommentsDTO CommentsBasicExample(){

        Example example = new Example();
        UserExample userExample = new UserExample();
        Channel channel = new Channel(example.ChannelExample());
        User user = new User(userExample.UserDTOExample()) ;

        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setCommentsId(1L);
        commentsDTO.setUserNum(user);
        commentsDTO.setContent("Hi my name is lsy");
        commentsDTO.setLikesCount(10L);
        commentsDTO.setChannel(channel);
        commentsDTO.setStarFan("f");


        return commentsDTO;
    }
}
