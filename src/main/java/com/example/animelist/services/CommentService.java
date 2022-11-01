package com.example.animelist.services;

import com.example.animelist.models.Comment;
import com.example.animelist.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsForAnime(Long animeId){
        return commentRepository.findByAnimeId(animeId);
    }
}
