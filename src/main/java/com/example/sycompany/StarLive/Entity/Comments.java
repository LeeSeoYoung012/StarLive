package com.example.sycompany.StarLive.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.time.LocalDateTime;


@Entity
public class Comments {

    @Id
    private Long commentsId;

    @JoinColumn(name = "user_num")
    private Long userNum;

    private String content;

    private Long likesCount;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;


    private LocalDateTime commentedAt;

    private Long parentCommentId;

    private String starOrFan;

}
