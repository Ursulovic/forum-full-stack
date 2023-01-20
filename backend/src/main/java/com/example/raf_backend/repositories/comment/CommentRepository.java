package com.example.raf_backend.repositories.comment;

import com.example.raf_backend.entities.Comment;

import java.util.List;

public interface CommentRepository {
    public boolean addComment(Comment comment);
    public List<Comment> findComment(Integer id);
}
