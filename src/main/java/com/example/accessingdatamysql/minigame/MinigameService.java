package com.example.accessingdatamysql.minigame;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MinigameService {
    private MinigameRepository minigameRepository;

    @Autowired
    public MinigameService(MinigameRepository minigameRepository) {
        this.minigameRepository = minigameRepository;
    }

    @Transactional
    public Minigame saveMinigame(Minigame minigame) throws DataAccessException {
        minigameRepository.save(minigame);
        return minigame;
    }

    public Optional<Minigame> findMinigame(Long id) {
        return minigameRepository.findById(id);
    }

    public Iterable<Minigame> findAllMinigames() {
        return minigameRepository.findAll();
    }

    public void deleteMinigame(Long id) {
        minigameRepository.deleteById(id);
    }

    public void deleteAllMinigames() {
        minigameRepository.deleteAll();
    }
}
