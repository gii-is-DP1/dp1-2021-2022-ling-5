package com.example.accessingdatamysql.comment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Comment saveComment(Comment n) {
        commentRepository.save(n);
        return n;
    }

    public Optional<Comment> findComment(Integer id) {
        return commentRepository.findById(id);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

}
