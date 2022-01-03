package com.example.accessingdatamysql.minigame;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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

  public List<Minigame> findAllMinigames() {
    return StreamSupport
      .stream(minigameRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  @Transactional
  public void deleteMinigame(Long id) {
    minigameRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllMinigames() {
    minigameRepository.deleteAll();
  }
}
