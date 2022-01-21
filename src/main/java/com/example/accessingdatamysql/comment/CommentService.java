package com.example.accessingdatamysql.comment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Comment saveComment(Comment n) throws DataAccessException{
        commentRepository.save(n);
        return n;
    }

    public Optional<Comment> findComment(Long id) throws DataAccessException{
        return commentRepository.findById(id);
    }

    public void deleteComment(Long id) throws DataAccessException{
        commentRepository.deleteById(id);
    }

}
