package com.example.sycompany.StarLive.DTO;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.VideoViewCount;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Id;
import javax.persistence.JoinColumn;


@Setter
@Getter
public class ChannelDTO {

    private Long channelId;

    private Long videoId;

    private Long commentsId;

    private String channelPicture;

    private Long likesCount;

    private Long channelViewsId;

    private Long memberCount;

    private String channelName;


    public void makeEntityToDTO(Channel channel){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(channel,this);
    }

}
