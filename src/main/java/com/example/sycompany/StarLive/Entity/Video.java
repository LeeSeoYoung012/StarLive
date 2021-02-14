package com.example.sycompany.StarLive.Entity;

import com.example.sycompany.StarLive.DTO.VideoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
public class Video  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoId;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    private String title;

    private LocalDateTime createdAt;

    private Long likeCount;

    private Long chattingId;

    @ManyToOne
    @JoinColumn(name="file_id")
    private VideoFile videoFile;


  public Video(VideoDTO videoDTO){
     this.videoId =  videoDTO.getVideoId();
     this.channel = videoDTO.getChannel();
     this.title = videoDTO.getTitle();
     this.createdAt = videoDTO.getCreatedAt();
     this.likeCount = videoDTO.getLikeCount();
     this.chattingId = videoDTO.getChattingId();
     this.videoFile = videoDTO.getVideoFile();
   }


}
