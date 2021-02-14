package com.example.sycompany.StarLive.Entity;


import com.example.sycompany.StarLive.DTO.VideoFileDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class VideoFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;

    private String fileUrl;

    private Long fileSize;

    public VideoFile (VideoFileDTO videoFileDTO){
        this.fileId = videoFileDTO.getFileId();
        this.fileSize = videoFileDTO.getFileSize();
        this.fileUrl = videoFileDTO.getFileUrl();
    }

}
