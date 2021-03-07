package com.example.sycompany.StarLive.Entity;

import com.example.sycompany.StarLive.DTO.CommentsDTO;
import com.example.sycompany.StarLive.Repository.CommentsRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    private Long commentsId;

    @ManyToOne
    @JoinColumn(name = "user_num")
    private User userNum;

    private String content;

    private Long likesCount;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;


    @CreationTimestamp
    private Timestamp commentedAt;

    private Long parentCommentId;

    private String starFan;

    @ManyToOne
    @JoinColumn(name="video_id")
    private Video videoId;

    private String imageUrl;

    public Comments(CommentsDTO commentsDTO){
        this.commentsId = commentsDTO.getCommentsId();
        this.userNum = commentsDTO.getUserNum();
        this.content = commentsDTO.getContent();
        this.likesCount = commentsDTO.getLikesCount();
        this.channel = commentsDTO.getChannel();
        this.commentedAt = commentsDTO.getCommentedAt();
        this.parentCommentId = commentsDTO.getParentCommentId();
        this.starFan = commentsDTO.getStarFan();
        this.videoId = commentsDTO.getVideoId();
        this.imageUrl = commentsDTO.getImageUrl();
    }

    public Long updateByCommentsDTO(CommentsDTO commentsDTO){
        this.commentsId = commentsDTO.getCommentsId();
        this.userNum = commentsDTO.getUserNum();
        this.content = commentsDTO.getContent();
        this.likesCount = commentsDTO.getLikesCount();
        this.channel = commentsDTO.getChannel();
        this.commentedAt = commentsDTO.getCommentedAt();
        this.parentCommentId = commentsDTO.getParentCommentId();
        this.starFan = commentsDTO.getStarFan();
        this.videoId = commentsDTO.getVideoId();
        this.imageUrl = commentsDTO.getImageUrl();
        return commentsId;
    }

}
