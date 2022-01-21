package com.example.accessingdatamysql.result;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional(readOnly = true)
  public Optional<Result> findResult(Long id) throws DataAccessException {
    return resultRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Result> findAllResults() throws DataAccessException {
    return StreamSupport
        .stream(resultRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<Result> findAllResultsByGame(Long gameId) throws DataAccessException {
    return StreamSupport
        .stream(resultRepository.findAll().spliterator(), false)
        .filter(result -> result.getGame().getId() == gameId)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<Result> findAllResultsByPlayer(Long playerId) throws DataAccessException {
    return StreamSupport
        .stream(resultRepository.findAll().spliterator(), false)
        .filter(result -> result.getPlayer().getId() == playerId)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteResult(Long id) throws DataAccessException {
    resultRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllResults() throws DataAccessException {
    resultRepository.deleteAll();
  }

  @Transactional
  public void deleteAllResultsByGame(Long gameId) throws DataAccessException {
    findAllResultsByGame(gameId)
        .stream()
        .forEach(result -> resultRepository.deleteById(result.getId()));
  }

  @Transactional
  public void deleteAllResultsByPlayer(Long playerId) throws DataAccessException {
    findAllResultsByPlayer(playerId)
        .stream()
        .forEach(result -> resultRepository.deleteById(result.getId()));
  }
}
