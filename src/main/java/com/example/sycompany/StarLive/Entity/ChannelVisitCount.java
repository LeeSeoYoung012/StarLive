package com.example.sycompany.StarLive.Entity;

import com.example.sycompany.StarLive.DTO.ChannelVisitCountDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class ChannelVisitCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
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
        this.channelVisitId = channelVisitCountDTO.getChannelVisitId();
    }



    public ChannelVisitCountDTO update(ChannelVisitCountDTO channelVisitCountDTO){
        this.channelVisitId = channelVisitCountDTO.getChannelVisitId();
        this.channelVisitDate = channelVisitCountDTO.getChannelVisitDate();
        this.channelVisitCount = channelVisitCountDTO.getChannelVisitCount();
        this.channel = channelVisitCountDTO.getChannel();
        return channelVisitCountDTO;
    }

    public ChannelVisitCountDTO makeEntityToDTO(ChannelVisitCount channelVisitCount){

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(channelVisitCount,ChannelVisitCountDTO.class);
    }

}
