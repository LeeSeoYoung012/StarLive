package com.example.sycompany.StarLive.DTO;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.VideoViewCount;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;


@Setter
@Getter
public class ChannelDTO {

    private Long channelId;

    private String channelPicture;

    private Long likesCount;

    private Long memberCount;

    private String channelName;

    private LocalDateTime createdAt;




}
