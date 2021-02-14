package com.example.sycompany.StarLive.Entity;


import com.example.sycompany.StarLive.DTO.VideoViewCountDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class VideoViewCount {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long viewId;

    private LocalDate viewsDate;

    private Long viewsCount;

    @ManyToOne
    @JoinColumn(name="video_id")
    private Video video;

    public VideoViewCount(VideoViewCountDTO videoViewCountDTO){
        this.viewId = videoViewCountDTO.getViewId();
        this.viewsCount = videoViewCountDTO.getViewsCount();
        this.video = videoViewCountDTO.getVideo();
        this.viewsDate = videoViewCountDTO.getViewsDate();
    }

    public int update(VideoViewCountDTO videoViewCountDTO){
        this.viewsCount = videoViewCountDTO.getViewsCount();
        this.video = videoViewCountDTO.getVideo();
        this.viewsDate = videoViewCountDTO.getViewsDate();
        return 1;
    }



}
