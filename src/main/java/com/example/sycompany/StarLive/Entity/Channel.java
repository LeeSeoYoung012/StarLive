package com.example.sycompany.StarLive.Entity;

import com.example.sycompany.StarLive.DTO.ChannelDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
public class Channel {

    @Id
    private Long channelId;


    private String channelPicture;

    private Long likesCount;

    private Long memberCount;

    private String channelName;

    private LocalDateTime createAt;

    public Channel(ChannelDTO channelDTO){
       this.channelId = channelDTO.getChannelId();
       this.channelPicture = channelDTO.getChannelPicture();
       this.likesCount = channelDTO.getLikesCount();
       this.memberCount = channelDTO.getMemberCount();
       this.channelName = channelDTO.getChannelName();
       this.createAt =channelDTO.getCreatedAt();
    }




}
