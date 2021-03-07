package com.example.sycompany.StarLive.DTO;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.VideoViewCount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;


@NoArgsConstructor
@Setter
@Getter
public class ChannelDTO {

    private Long channelId;

    private String channelPicture;

    private Long likesCount;

    private Long memberCount;

    private String channelName;

    private LocalDateTime createdAt;


    public ChannelDTO(Channel channel){

        this.channelId = channel.getChannelId();
        this.channelPicture = channel.getChannelPicture();
        this.likesCount = channel.getLikesCount();
        this.memberCount = channel.getMemberCount();
        this.channelName = channel.getChannelName();
        this.createdAt = channel.getCreateAt();

    }



}
