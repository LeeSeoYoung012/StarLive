package com.example.sycompany.StarLive.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
public class Channel {

    @Id
    private Long channelId;

    @JoinColumn(name= "video_id")
    private Long videoId;


    private String channelPicture;

    private Long likesCount;

    private Long memberCount;

    private String channelName;

    private LocalDateTime createAt;


}
