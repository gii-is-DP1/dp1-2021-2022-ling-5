package com.example.accessingdatamysql.result;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

  private ResultRepository resultRepository;

  @Autowired
  public ResultService(ResultRepository resultRepository) {
    this.resultRepository = resultRepository;
  }

  @Transactional
  public Result saveResult(Result result) throws DataAccessException {
    resultRepository.save(result);
    return result;
  }

  public Optional<Result> findResult(Long id) {
    return resultRepository.findById(id);
  }

  public List<Result> findAllResults() {
    return StreamSupport
      .stream(resultRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  public List<Result> findAllResultsByGame(Long gameId) {
    return StreamSupport
      .stream(resultRepository.findAll().spliterator(), false)
      .filter(result -> result.getGame().getId() == gameId)
      .collect(Collectors.toList());
  }

  public List<Result> findAllResultsByPlayer(Long playerId) {
    return StreamSupport
      .stream(resultRepository.findAll().spliterator(), false)
      .filter(result -> result.getPlayer().getId() == playerId)
      .collect(Collectors.toList());
  }

  @Transactional
  public void deleteResult(Long id) {
    resultRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllResults() {
    resultRepository.deleteAll();
  }

  @Transactional
  public void deleteAllResultsByGame(Long gameId) {
    findAllResultsByGame(gameId)
      .stream()
      .forEach(result -> resultRepository.deleteById(result.getId()));
  }

  @Transactional
  public void deleteAllResultsByPlayer(Long playerId) {
    findAllResultsByPlayer(playerId)
      .stream()
      .forEach(result -> resultRepository.deleteById(result.getId()));
  }
}
