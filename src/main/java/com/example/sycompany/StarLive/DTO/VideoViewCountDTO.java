package com.example.sycompany.StarLive.DTO;


import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoViewCount;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class VideoViewCountDTO {

    private Long viewId;
    private LocalDate viewsDate;
    private Long viewsCount;
    private Video video;


    public void makeEntityToDTO(VideoViewCount videoviewCount){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(videoviewCount,this);
    }
}
