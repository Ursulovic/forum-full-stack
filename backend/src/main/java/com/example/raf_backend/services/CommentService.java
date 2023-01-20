package com.example.raf_backend.services;

import com.example.raf_backend.entities.Comment;
import com.example.raf_backend.repositories.comment.CommentRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public boolean addComment( Comment comment) {
        return this.commentRepository.addComment(comment);
    }

    public List<Comment> findComment( Integer id) {
        return this.commentRepository.findComment(id);
    }


}
