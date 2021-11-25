package com.example.accessingdatamysql.game;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        try{
            gameRepository.save(game);
            return game;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return game;
        }
    }

    public Optional<Game> findGame(Long id) {
        return gameRepository.findById(id);
    }

    public List<Game> findAllGames() {
        return StreamSupport.stream(gameRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public void deleteAllGames() {
        gameRepository.deleteAll();
    }
}
