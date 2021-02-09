package com.example.sycompany.StarLive.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserRecentplayList {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "video_id")
    Video video;

    Long userNum;
}
