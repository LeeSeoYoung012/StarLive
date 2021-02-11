package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

    @Query("select Count(m) from Comments m where m.channel = :channel and m.starOrFan = :starorfan")
    Long findCommentsCountByChannelIdAndStarOrFan(@Param("channel") Channel channel, @Param("starorfan")String starorfan);

}
