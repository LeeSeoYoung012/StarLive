package com.example.sycompany.StarLive.DTO;


import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoFile;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Setter
@Getter
public class VideoDTO {
   private Long videoId;
   private Channel channel;
   private String title;
   private LocalDateTime createdAt;
   private Long likeCount;
   private Long videoViewsId;
   private Long chattingId;
   private VideoFile videoFile;

   public void makeEntityToDTO(Video video){

      ModelMapper modelMapper = new ModelMapper();
      modelMapper.map(video,this);
   }

}
