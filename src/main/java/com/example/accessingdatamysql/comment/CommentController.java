package com.example.accessingdatamysql.comment;

import java.time.LocalDate;

import com.example.accessingdatamysql.forum.ForumService;
import com.example.accessingdatamysql.user.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ForumService forumService;

    @PostMapping(value = "/new") // Map ONLY POST Requests
    public @ResponseBody Comment addNewComment(@RequestBody String text, @RequestBody Long userId,
            @RequestBody Integer forumId) {
        Comment n = new Comment();
        n.setText(text);
        n.setDate(LocalDate.now());
        n.setUser(accountService.findAccount(userId).get());
        n.setForum(forumService.findForum(forumId).get());
        return this.commentService.saveComment(n);
    }

    @PutMapping(value = "/id")
    public @ResponseBody Comment updateComment(@RequestBody String text, @PathVariable Integer id) {
        this.commentService.findComment(id).map(comment -> {
            comment.setText(text);
            return this.commentService.saveComment(comment);
        }).orElse(null);
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody String deleteComment(@PathVariable Integer id) {
        this.commentService.deleteComment(id);
        return "Deleted";
    }
}
