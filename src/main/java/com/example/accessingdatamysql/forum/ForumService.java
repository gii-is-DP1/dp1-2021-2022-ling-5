package com.example.accessingdatamysql.forum;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForumService {

    @Autowired
    private ForumRepository forumRepository;

    @Transactional
    public Forum saveForum(Forum n) {
        forumRepository.save(n);
        return n;
    }

    public List<Forum> findAllForums() {
        Iterable<Forum> f = forumRepository.findAll();
        List<Forum> ls = StreamSupport
                .stream(f.spliterator(), false)
                .collect(Collectors.toList());
        ls.sort(Comparator.comparing(o -> ((Forum) o).hasComment()?((Forum) o).lastCommentDate():((Forum) o).getCreationDate()).reversed());
        return ls;
    }

    public Optional<Forum> findForum(Long id) {
        return forumRepository.findById(id);
    }

    public void deleteService(Long id) {
        Optional<Forum> f = this.findForum(id);
        if (f.isPresent()) {
            forumRepository.delete(f.get());
        }

    }

}
