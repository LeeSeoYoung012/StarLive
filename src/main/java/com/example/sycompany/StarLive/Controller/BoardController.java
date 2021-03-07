package com.example.sycompany.StarLive.Controller;


import com.example.sycompany.StarLive.DTO.CommentsDTO;
import com.example.sycompany.StarLive.Entity.Channel;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Repository.ChannelRepository;
import com.example.sycompany.StarLive.Repository.CommentsRepository;
import com.example.sycompany.StarLive.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BoardController {

    final CommentsRepository commentsRepository;
    final ChannelRepository channelRepository;
    final BoardService boardService;

    @GetMapping("/fanPost/{channelId}")
    List<Comments> getFanPostList(@PathVariable Long channelId,@RequestParam String orderType, @RequestParam String contentType){
        Channel channel = channelRepository.findByChannelId(channelId);

        switch (contentType) {
            case "all":
                switch (orderType) {
                    case "DateAsc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByCommentedAtAsc(channel, "f");
                    case "DateDesc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByCommentedAtDesc(channel, "f");
                    case "LikesCount":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByLikesCount(channel, "f");
                }
                break;
            case "image":
                switch (orderType) {
                    case "DateAsc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByCommentedAtAsc(channel, "f");
                    case "DateDesc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByCommentedAtDesc(channel, "f");
                    case "LikesCount":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByLikesCount(channel, "f");
                }
                break;
            case "video":
                switch (orderType) {
                    case "DateAsc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByCommentedAtAsc(channel, "f");
                    case "DateDesc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByCommentedAtDesc(channel, "f");
                    case "LikesCount":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByLikesCount(channel, "f");
                }
                break;
        }
        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByCommentedAtDesc(channel,"f");
    }

    @GetMapping("/starPost/{channelId}")
    List<Comments> getStarPostList(@PathVariable Long channelId,@RequestParam String orderType,@RequestParam String contentType){
        Channel channel = channelRepository.findByChannelId(channelId);

        switch (contentType) {
            case "all":
                switch (orderType) {
                    case "DateAsc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByCommentedAtAsc(channel, "s");
                    case "DateDesc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByCommentedAtDesc(channel, "s");
                    case "LikesCount":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByLikesCount(channel, "s");
                }
                break;
            case "image":
                switch (orderType) {
                    case "DateAsc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByCommentedAtAsc(channel, "s");
                    case "DateDesc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByCommentedAtDesc(channel, "s");
                    case "LikesCount":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndImageUrlNotNullOrderByLikesCount(channel, "s");
                }
                break;
            case "video":
                switch (orderType) {
                    case "DateAsc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByCommentedAtAsc(channel, "s");
                    case "DateDesc":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByCommentedAtDesc(channel, "s");
                    case "LikesCount":
                        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullAndVideoIdNotNullOrderByLikesCount(channel, "s");
                }
                break;
        }
        return commentsRepository.findByChannelAndStarFanEqualsAndParentCommentIdIsNullOrderByCommentedAtDesc(channel,"s");
    }

    @GetMapping("/comments/{postId}")
    List<Comments> getCommentsList(@PathVariable Long postId){
        return commentsRepository.findByParentCommentIdEquals(postId);
    }

    @PostMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    Comments postComments(@RequestBody CommentsDTO commentsDTO)
    {
     Comments comments = new Comments(commentsDTO);
     commentsRepository.save(comments);
     return comments;
    }

    @PutMapping("/comments")
    Long putComments(@RequestBody CommentsDTO commentsDTO)
    {
        Long id = commentsDTO.getCommentsId();
        return boardService.updateService(id, commentsDTO);
    }

    @DeleteMapping("/comments")
    Long deleteComments(@RequestBody Long commentsId)
    {
       commentsRepository.deleteById(commentsId);
       boardService.deleteService(commentsId);
       return commentsId;
    }









}
