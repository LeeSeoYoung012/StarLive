package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.Comments;
import com.example.sycompany.StarLive.Entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> {


}
