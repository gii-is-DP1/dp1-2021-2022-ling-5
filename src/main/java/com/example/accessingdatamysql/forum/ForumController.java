package com.example.accessingdatamysql.forum;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @PostMapping(value = "/new") 
    public @ResponseBody Forum addNewForum(@RequestBody Forum forum) {
        return this.forumService.saveForum(forum);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody Forum updateForum(@RequestBody Forum newForum, @PathVariable Long id) {
        this.forumService.findForum(id).map(forum -> {
            forum.setName(newForum.getName());
            forum.setCreationDate(newForum.getCreationDate());
            return this.forumService.saveForum(forum);
        }).orElse(null);
        return null;
    }

    @GetMapping(value = "/all")
    public @ResponseBody List<Forum> getAllForums() {
        return this.forumService.findAllForums();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody Optional<Forum> getForumById(@PathVariable Long id) {
        return this.forumService.findForum(id);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody String deleteForum(@PathVariable Long id) {
        this.forumService.deleteService(id);
        return "Deleted";
    }
}
