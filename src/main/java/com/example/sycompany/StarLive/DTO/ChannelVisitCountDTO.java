package com.example.sycompany.StarLive.DTO;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.ChannelVisitCount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class ChannelVisitCountDTO {

    private Long channelVisitId;

    private LocalDate channelVisitDate;
    private Long channelVisitCount;
    private Channel channel;


    public ChannelVisitCountDTO (ChannelVisitCount channelVisitCount){
        this.channelVisitId = channelVisitCount.getChannelVisitId();
        this.channelVisitDate = channelVisitCount.getChannelVisitDate();
        this.channel = channelVisitCount.getChannel();
        this.channelVisitCount = channelVisitCount.getChannelVisitCount();
    }

}
