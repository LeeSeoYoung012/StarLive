package com.example.sycompany.StarLive.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class VideoViewCount {

    @Id
    private Long viewId;

    private LocalDate viewsDate;

    private Long viewsCount;

    @ManyToOne
    @JoinColumn(name="video_id")
    private Video video;



}
