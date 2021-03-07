package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

    @Query("select Count(m) from Comments m where m.channel = :channel and m.starFan = :starFan")
    Long findCommentsCountByChannelIdAndStarOrFan(@Param("channel") Channel channel, @Param("starFan")String starFan);

    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByCommentedAtAsc(Channel channel, String starFan);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByCommentedAtDesc(Channel channel, String starFan);
    List<Comments> findByParentCommentIdEquals(Long parentCommentId);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByLikesCount(Channel channel, String starFan);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlIsNotNullOrderByCommentedAtAsc(Channel channel, String starFan);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByCommentedAtAsc(Channel channel, String starFan);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByCommentedAtDesc(Channel channel, String starFan);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByLikesCount(Channel channel, String starFan);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByCommentedAtAsc(Channel channel, String starFan);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByCommentedAtDesc(Channel channel, String starFan);
    List<Comments> findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByLikesCount(Channel channel, String starFan);
}
