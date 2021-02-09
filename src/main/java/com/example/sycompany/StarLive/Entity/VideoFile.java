package com.example.sycompany.StarLive.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class VideoFile {

    @Id
    private Long fileId;

    private String fileUrl;

    private Long fileSize;


}
