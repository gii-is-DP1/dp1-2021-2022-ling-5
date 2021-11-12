package com.example.accessingdatamysql.user;

import java.util.Optional;

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

    public Iterable<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }
}
