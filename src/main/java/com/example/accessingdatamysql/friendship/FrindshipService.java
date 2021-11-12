package com.example.accessingdatamysql.friendship;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class FrindshipService {
    private FriendshipRepository friendshipRepository;

    @Autowired
    public FrindshipService(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    @Transactional
    public Friendship saveFriendship(Friendship friendship) throws DataAccessException {
        friendshipRepository.save(friendship);
        return friendship;
    }

    public Optional<Friendship> findFriendship(Long id) {
        return friendshipRepository.findById(id);
    }

    public Iterable<Friendship> findAllFriendships() {
        return friendshipRepository.findAll();
    }

    public void deleteFriendship(Long id) {
        friendshipRepository.deleteById(id);
    }

    public void deleteAllFriendships() {
        friendshipRepository.deleteAll();
    }
}
