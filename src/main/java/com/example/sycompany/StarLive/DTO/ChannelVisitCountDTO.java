package com.example.sycompany.StarLive.DTO;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.ChannelVisitCount;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class ChannelVisitCountDTO {

    private Long channelVisitId;

    private LocalDate channelVisitDate;
    private Long ChannelVisitCount;
    private Channel channel;

    public void makeEntityToDTO(ChannelVisitCount channelVisitCount){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(channelVisitCount,this);
    }
}
