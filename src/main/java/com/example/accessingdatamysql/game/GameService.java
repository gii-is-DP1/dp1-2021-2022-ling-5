package com.example.accessingdatamysql.game;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Game saveGame(Game game) throws DataAccessException {
        gameRepository.save(game);
        return game;
    }

    public Optional<Game> findGame(Long id) {
        return gameRepository.findById(id);
    }

    public Iterable<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public void deleteAllGames() {
        gameRepository.deleteAll();
    }
}