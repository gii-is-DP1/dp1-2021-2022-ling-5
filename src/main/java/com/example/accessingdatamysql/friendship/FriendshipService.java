package com.example.accessingdatamysql.friendship;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendshipService {

  private FriendshipRepository friendshipRepository;

  @Autowired
  public FriendshipService(FriendshipRepository friendshipRepository) {
    this.friendshipRepository = friendshipRepository;
  }

  @Transactional
  public Friendship saveFriendship(Friendship friendship)
      throws DataAccessException {
    friendshipRepository.save(friendship);
    return friendship;
  }

  @Transactional(readOnly = true)
  public Optional<Friendship> findFriendship(Long id) throws DataAccessException {
    return friendshipRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Friendship> findAllFriendships() throws DataAccessException {
    return StreamSupport
        .stream(friendshipRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<Friendship> findAllFriendshipsByRequester(Long requesterId) throws DataAccessException {
    return StreamSupport
        .stream(friendshipRepository.findAll().spliterator(), false)
        .filter(friendship -> friendship.getRequester().getId() == requesterId)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<Friendship> findAllFriendshipsByRequested(Long requestedId) throws DataAccessException {
    return StreamSupport
        .stream(friendshipRepository.findAll().spliterator(), false)
        .filter(friendship -> friendship.getRequested().getId() == requestedId)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteFriendship(Long id) throws DataAccessException {
    friendshipRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllFriendships() throws DataAccessException {
    friendshipRepository.deleteAll();
  }

  @Transactional
  public void deleteAllFriendshipsByRequester(Long playerId) throws DataAccessException {
    findAllFriendshipsByRequester(playerId)
        .stream()
        .forEach(
            friendship -> friendshipRepository.deleteById(friendship.getId()));
  }

  @Transactional
  public void deleteAllFriendshipsByRequested(Long playerId) throws DataAccessException {
    findAllFriendshipsByRequested(playerId)
        .stream()
        .forEach(
            friendship -> friendshipRepository.deleteById(friendship.getId()));
  }
}
