package com.example.sycompany.StarLive.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Channel {

    @Id
    private Long channelId;

    @JoinColumn(name= "video_id")
    private Long videoId;

    @JoinColumn(name ="comments_id")
    private Long commentsId;

    private String channelPicture;

    private Long likesCount;

    private Long memberCount;

    private String channelName;


}
