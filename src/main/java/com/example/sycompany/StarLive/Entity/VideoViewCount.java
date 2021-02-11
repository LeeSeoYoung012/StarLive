package com.example.sycompany.StarLive.Entity;


import com.example.sycompany.StarLive.DTO.VideoViewCountDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class VideoViewCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long viewId;

    private LocalDate viewsDate;

    private Long viewsCount;

    @ManyToOne
    @JoinColumn(name="video_id")
    private Video video;

    public VideoViewCount(VideoViewCountDTO videoViewCountDTO){
        this.viewsCount = videoViewCountDTO.getViewsCount();
        this.video = videoViewCountDTO.getVideo();
        this.viewsDate = videoViewCountDTO.getViewsDate();
    }

    public void update(VideoViewCountDTO videoViewCountDTO){
        this.viewsCount = videoViewCountDTO.getViewsCount();
        this.video = videoViewCountDTO.getVideo();
        this.viewsDate = videoViewCountDTO.getViewsDate();
    }


}
