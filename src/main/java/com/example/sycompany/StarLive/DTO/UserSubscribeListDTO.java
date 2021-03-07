package com.example.sycompany.StarLive.DTO;


import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.UserSubscribeList;
import com.example.sycompany.StarLive.Entity.Video;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
public class UserSubscribeListDTO {

    private Long id;
    private Channel channel;
    private Long userNum;


    public UserSubscribeListDTO(UserSubscribeList userSubscribeList){
        this.id = userSubscribeList.getId();
        this.channel = userSubscribeList.getChannel();
        this.userNum = userSubscribeList.getUserNum();
    }


}
