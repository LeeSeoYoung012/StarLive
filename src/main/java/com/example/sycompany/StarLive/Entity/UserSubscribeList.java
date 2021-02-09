package com.example.sycompany.StarLive.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserSubscribeList {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    Channel channel;

    Long userNum;
}
