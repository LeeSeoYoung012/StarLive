package com.example.sycompany.StarLive.DTO;


import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class VideoDTO {
   private Long videoId;
   private Channel channel;
   private String title;
   private LocalDateTime createdAt;
   private Long likeCount;
   private Long chattingId;
   private VideoFile videoFile;

   public VideoDTO (Video video){
      this.setChattingId(video.getChattingId());
      this.setVideoId(video.getVideoId());
      this.setChannel(video.getChannel());
      this.setCreatedAt(video.getCreatedAt());
      this.setLikeCount(video.getLikeCount());
      this.setTitle(video.getTitle());
      this.setVideoFile(video.getVideoFile());
   }

}
