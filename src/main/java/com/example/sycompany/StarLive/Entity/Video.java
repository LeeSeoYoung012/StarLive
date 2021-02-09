package com.example.sycompany.StarLive.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Video  {

    @Id
    private Long videoId;

    @JoinColumn(name = "channel_id")
    private Long channelId;

    private String title;

    private LocalDateTime createdAt;

    private Long likeCount;

    @ManyToOne
    @JoinColumn(name = "comments_id")
    private Comments commentsId;

    private Long chattingId;

    @ManyToOne
    @JoinColumn(name="file_id")
    private VideoFile fileId;





}
