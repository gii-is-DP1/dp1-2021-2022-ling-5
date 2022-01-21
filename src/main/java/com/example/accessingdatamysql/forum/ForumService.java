package com.example.accessingdatamysql.forum;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForumService {

    @Autowired
    private ForumRepository forumRepository;

    @Transactional
    public Forum saveForum(Forum n) throws DataAccessException {
        forumRepository.save(n);
        return n;
    }

    @Transactional(readOnly = true)
    public List<Forum> findAllForums() throws DataAccessException {
        Iterable<Forum> f = forumRepository.findAll();
        List<Forum> ls = StreamSupport
                .stream(f.spliterator(), false)
                .collect(Collectors.toList());
        ls.sort(Comparator
                .comparing(
                        o -> ((Forum) o).hasComment() ? ((Forum) o).lastCommentDate() : ((Forum) o).getCreationDate())
                .reversed());
        return ls;
    }

    @Transactional(readOnly = true)
    public Optional<Forum> findForum(Long id) throws DataAccessException {
        return forumRepository.findById(id);
    }

    @Transactional
    public void deleteService(Long id) throws DataAccessException {
        Optional<Forum> f = this.findForum(id);
        if (f.isPresent()) {
            forumRepository.delete(f.get());
        }

    }

}
