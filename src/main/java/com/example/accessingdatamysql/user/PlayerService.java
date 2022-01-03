package com.example.accessingdatamysql.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private PlayerRepository playerRepository;

  @Autowired
  public PlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Transactional
  public Player savePlayer(Player player) throws DataAccessException {
    playerRepository.save(player);
    return player;
  }

  public Optional<Player> findPlayer(Long id) {
    return playerRepository.findById(id);
  }

  public List<Player> findAllPlayers() {
    return StreamSupport
      .stream(playerRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  public List<Player> findAllPlayersByRole(Long roleId) {
    return StreamSupport
      .stream(playerRepository.findAll().spliterator(), false)
      .filter(player -> player.getRole().getId() == roleId)
      .collect(Collectors.toList());
  }

  // public List<Player> findAllPlayersByGame(Long gameId) {
  // System.out.println("gameId: " + gameId);
  // System.out.println("GAMEEE: " + gameRepository.findById(gameId).get());
  // return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
  // .filter(player ->
  // player.getGamesPlayed().contains(gameRepository.findById(gameId).get()))
  // .collect(Collectors.toList());
  // }
  @Transactional
  public void deletePlayer(Long id) {
    playerRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllPlayers() {
    playerRepository.deleteAll();
  }

  @Transactional
  public void deleteAllPlayersByRole(Long roleId) {
    findAllPlayersByRole(roleId)
      .stream()
      .forEach(player -> playerRepository.deleteById(player.getId()));
  }

  public List<Player> findByNickname(String nickname) {
    List<Player> players = new ArrayList<Player>();
    for (Player p : playerRepository.findAll()) {
      if (p.getNickname().equals(nickname)) {
        players.add(p);
      }
    }
    return players;
  }
}
