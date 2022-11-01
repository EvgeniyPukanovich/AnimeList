package com.example.animelist.repositories;

import com.example.animelist.models.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByAnimeId(Long animeId);
}
