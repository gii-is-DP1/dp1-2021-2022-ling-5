package com.example.accessingdatamysql.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import com.example.accessingdatamysql.game.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    private GameRepository gameRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Player savePlayer(Player player) throws DataAccessException {
        System.out.println("preuba" + player);
        playerRepository.save(player);
        return player;
    }

    public Optional<Player> findPlayer(Long id) {
        return playerRepository.findById(id);
    }

    public List<Player> findAllPlayers() {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Player> findAllPlayersByRole(Long roleId) {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .filter(player -> player.getRole().getId() == roleId).collect(Collectors.toList());
    }

    public List<Player> findAllPlayersByGame(Long gameId) {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .filter(player -> player.getGamesPlayed().contains(gameRepository.findById(gameId).get()))
                .collect(Collectors.toList());
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }

    public void deleteAllPlayersByRole(Long roleId) {
        findAllPlayersByRole(roleId).stream().forEach(player -> playerRepository.deleteById(player.getId()));
    }
}
