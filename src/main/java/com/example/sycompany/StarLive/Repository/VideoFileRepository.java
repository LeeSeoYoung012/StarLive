package com.example.sycompany.StarLive.Repository;

import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoFileRepository extends JpaRepository<VideoFile,Long> {
}
