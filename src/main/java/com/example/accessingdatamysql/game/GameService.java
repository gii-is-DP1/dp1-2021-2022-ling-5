package com.example.accessingdatamysql.game;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import com.example.accessingdatamysql.user.PlayerService;

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

    @Autowired
    private PlayerService playerService;

    @Transactional
    public Game saveGame(Game game) throws DataAccessException {
        gameRepository.save(game);
        return game;
    }

    public Optional<Game> findGame(Long id) {
        return gameRepository.findById(id);
    }

    public Optional<Game> findGameByName(String name) {
        return StreamSupport.stream(gameRepository.findAll().spliterator(), false)
                .filter(game -> game.getName().equals(name)).findFirst();
    }

    public static List<Game> findAllGames() {
        return StreamSupport.stream(gameRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public void deleteAllGames() {
        gameRepository.deleteAll();
    }

    public List<Game> getGamesByPlayer(Long playerId){
        List<Game> games = this.findAllGames();
        return games.stream().filter(g->g.getPlayers().contains(playerService.findPlayer(playerId).get()))
            .collect(Collectors.toList());
    }
}
