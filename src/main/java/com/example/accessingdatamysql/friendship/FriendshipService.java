package com.example.accessingdatamysql.friendship;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class FriendshipService {
    private FriendshipRepository friendshipRepository;

    @Autowired
    public FriendshipService(FriendshipRepository friendshipRepository) {
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

    public List<Friendship> findAllFriendshipsByRequester(Long requesterId) {
        return StreamSupport.stream(friendshipRepository.findAll().spliterator(), false)
                .filter(friendship -> friendship.getRequester().getId() == requesterId).collect(Collectors.toList());
    }

    public List<Friendship> findAllFriendshipsByRequested(Long requestedId) {
        return StreamSupport.stream(friendshipRepository.findAll().spliterator(), false)
                .filter(friendship -> friendship.getRequested().getId() == requestedId).collect(Collectors.toList());
    }

    public void deleteFriendship(Long id) {
        friendshipRepository.deleteById(id);
    }

    public void deleteAllFriendships() {
        friendshipRepository.deleteAll();
    }

    public void deleteAllFriendshipsByRequester(Long playerId) {
        findAllFriendshipsByRequester(playerId).stream().forEach(friendship -> friendshipRepository.deleteById(friendship.getId()));
    }

    public void deleteAllFriendshipsByRequested(Long playerId) {
        findAllFriendshipsByRequested(playerId).stream().forEach(friendship -> friendshipRepository.deleteById(friendship.getId()));
    }
}
