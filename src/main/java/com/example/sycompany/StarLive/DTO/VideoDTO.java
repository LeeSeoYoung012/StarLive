package com.example.sycompany.StarLive.DTO;


import com.example.sycompany.StarLive.Entity.Video;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Setter
@Getter
public class VideoDTO {
   private Long videoId;
   private Long channelId;
   private String title;
   private LocalDateTime createdAt;
   private Long likeCount;
   private Long videoViewsId;
   private Long commentsId;
   private Long chattingId;
   private Long fileId;

   public void makeEntityToDTO(Video video){

      ModelMapper modelMapper = new ModelMapper();
      modelMapper.map(video,this);
   }

}
