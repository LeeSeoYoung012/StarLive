package com.example.sycompany.StarLive.Entity;

import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ChannelVisitCount {

    @Id
    private Long channelVisitId;

    private LocalDate channelVisitDate;

    private Long channelVisitCount;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;


    public ChannelVisitCount(ChannelVisitCountDTO channelVisitCountDTO){
        this.channelVisitCount = channelVisitCountDTO.getChannelVisitCount();
        this.channelVisitDate = channelVisitCountDTO.getChannelVisitDate();
        this.channel = channelVisitCountDTO.getChannel();
    }
    public void update(ChannelVisitCountDTO channelVisitCountDTO){
        this.channelVisitDate = channelVisitCountDTO.getChannelVisitDate();
        this.channelVisitCount = channelVisitCountDTO.getChannelVisitCount();
        this.channel = channelVisitCountDTO.getChannel();
    }

}
