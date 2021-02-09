package com.example.sycompany.StarLive.Entity;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class UserVideo {

    @Id
    private Long videoId;

    @JoinColumn(name="user_num")
    private Long userNum;

    @JoinColumn(name="recent_playlist")
    private Long recentPlaylist;

    @JoinColumn(name="book_marklist")
    private Long bookMarklist;

    @JoinColumn(name="subscribe_list")
    private Long subscribeList;




}
