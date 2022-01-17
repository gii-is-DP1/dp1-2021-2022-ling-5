package com.example.accessingdatamysql.comment;


import com.example.accessingdatamysql.forum.ForumService;
import com.example.accessingdatamysql.user.PlayerService;

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
    private PlayerService playerService;

    @Autowired
    private ForumService forumService;

    @PostMapping(value = "/{playerId}/{forumId}") // Map ONLY POST Requests
    public @ResponseBody Comment addNewComment(@RequestBody Comment comment,@PathVariable Long playerId,@PathVariable Long forumId) {
        comment.setForum(forumService.findForum(forumId).get());
        comment.setUser(playerService.findPlayer(playerId).get());
        return this.commentService.saveComment(comment);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody Comment updateComment(@RequestBody String text, @PathVariable Long id) {
        this.commentService.findComment(id).map(comment -> {
            comment.setText(text);
            return this.commentService.saveComment(comment);
        }).orElse(null);
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody String deleteComment(@PathVariable Long id) {
        this.commentService.deleteComment(id);
        return "Deleted";
    }
}
