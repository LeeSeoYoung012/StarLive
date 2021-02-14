package com.example.sycompany.StarLive.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoFileDTO {

    private Long fileId;

    private String fileUrl;

    private Long fileSize;

}
