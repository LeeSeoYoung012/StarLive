package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.CommentsDTO;
import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Repository.CommentsRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@NoArgsConstructor
@Service
public class BoardService {

    CommentsRepository commentsRepository;
    public void deleteService(Long commentsId){

      List<Comments> commentsList = commentsRepository.findByParentCommentIdEquals(commentsId);

      for(int i=0; i<commentsList.size(); i++){

          Long id = commentsList.get(i).getCommentsId();
          commentsRepository.deleteById(id);

          List<Comments> commentsList2 = commentsRepository.findByParentCommentIdEquals(id);

          for(int j=0; j<commentsList2.size(); j++){
              commentsRepository.deleteById(commentsList2.get(i).getCommentsId());
          }
      }
   }

    public Long updateService(Long commentsId, CommentsDTO commentsDTO){

        Comments comments = commentsRepository.findById(commentsId).orElseThrow( () -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        return comments.updateByCommentsDTO(commentsDTO);

    }



}
