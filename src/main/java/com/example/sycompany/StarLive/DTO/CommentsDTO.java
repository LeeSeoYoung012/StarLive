package com.example.sycompany.StarLive.DTO;


import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Entity.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommentsDTO {

    private Long commentsId;

    private User userNum;

    private String content;

    private Long likesCount;

    private Channel channel;

    private Timestamp commentedAt;

    private Long parentCommentId;

    private String starFan;

    private Video videoId;

    private String imageUrl;


    public CommentsDTO(Comments comments){
        this.commentsId = comments.getCommentsId();
        this.userNum = comments.getUserNum();
        this.content = comments.getContent();
        this.likesCount = comments.getLikesCount();
        this.channel = comments.getChannel();
        this.commentedAt = comments.getCommentedAt();
        this.parentCommentId = comments.getParentCommentId();
        this.starFan = comments.getStarFan();
        this.videoId = comments.getVideoId();
        this.imageUrl = comments.getImageUrl();
    }

}
