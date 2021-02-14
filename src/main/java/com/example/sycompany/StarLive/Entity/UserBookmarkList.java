package com.example.sycompany.StarLive.Entity;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Entity
public class UserBookmarkList {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "video_id")
    Video video;

    Long userNum;
}
