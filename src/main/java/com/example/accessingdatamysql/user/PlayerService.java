package com.example.accessingdatamysql.user;

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
        System.out.println("preuba" + player);
        playerRepository.save(player);
        return player;
    }

    public Optional<Player> findPlayer(Long id) {
        return playerRepository.findById(id);
    }

    public Iterable<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> findAllPlayersByRole(Long roleId) {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .filter(player -> player.getRole().getId() == roleId).collect(Collectors.toList());
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
