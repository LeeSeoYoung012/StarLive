package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Video;
import com.example.sycompany.StarLive.Entity.VideoViewCount;

import java.util.List;

public interface CompareViewCount {
    List<Video> compareVideoViewCount(Video video);
    List<Channel> compareChannelViewCount(Channel channel);
}

