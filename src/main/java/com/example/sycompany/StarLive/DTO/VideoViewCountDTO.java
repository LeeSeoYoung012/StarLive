package com.example.sycompany.StarLive.DTO;


import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoViewCount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VideoViewCountDTO {

    private Long viewId;
    private LocalDate viewsDate;
    private Long viewsCount;
    private Video video;


    public VideoViewCountDTO (VideoViewCount videoViewCount){
        this.viewId = videoViewCount.getViewId();
        this.viewsDate = videoViewCount.getViewsDate();
        this.viewsCount = videoViewCount.getViewsCount();
        this.video = videoViewCount.getVideo();
    }
}
