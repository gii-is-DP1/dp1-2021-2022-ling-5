package com.example.accessingdatamysql.playerfigures;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.user.Player;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerFiguresService {

  private PlayerFiguresRepository playerFiguresRepository;

  @Autowired
  private FigureService figureService;

  @Autowired
  public PlayerFiguresService(PlayerFiguresRepository playerFiguresRepository) {
    this.playerFiguresRepository = playerFiguresRepository;
  }

  @Transactional
  public PlayerFigures savePlayerFigures(PlayerFigures playerFigures)
      throws DataAccessException {
    playerFiguresRepository.save(playerFigures);
    return playerFigures;
  }

  @Transactional(readOnly = true)
  public Optional<PlayerFigures> findPlayerFigures(Long id) throws DataAccessException {
    return playerFiguresRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<PlayerFigures> findAllPlayerFigures() throws DataAccessException {
    return StreamSupport
        .stream(playerFiguresRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<PlayerFigures> findAllPlayerFiguresByPlayer(Long playerId) throws DataAccessException {
    return StreamSupport
        .stream(playerFiguresRepository.findAll().spliterator(), false)
        .filter(result -> result.getPlayer().getId() == playerId)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<PlayerFigures> findAllPlayerFiguresByFigure(Long figureId) throws DataAccessException {
    return StreamSupport
        .stream(playerFiguresRepository.findAll().spliterator(), false)
        .filter(result -> result.getFigure().getId() == figureId)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deletePlayerFigures(Long id) throws DataAccessException {
    playerFiguresRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllPlayerFigures() throws DataAccessException {
    playerFiguresRepository.deleteAll();
  }

  @Transactional
  public void deleteAllPlayerFiguresByPlayer(Long playerId) throws DataAccessException {
    findAllPlayerFiguresByPlayer(playerId)
        .stream()
        .forEach(result -> playerFiguresRepository.deleteById(result.getId()));
  }

  @Transactional
  public void deleteAllPlayerFiguresByFigure(Long figureId) throws DataAccessException {
    findAllPlayerFiguresByFigure(figureId)
        .stream()
        .forEach(result -> playerFiguresRepository.deleteById(result.getId()));
  }

  @Transactional(readOnly = true)
  public PlayerFigures getByData(Long figureId, Long playerId) throws DataAccessException {
    return StreamSupport
        .stream(playerFiguresRepository.findAll().spliterator(), false)
        .filter(result -> result.getFigure().getId() == figureId)
        .filter(result -> result.getPlayer().getId() == playerId)
        .collect(Collectors.toList())
        .get(0);
  }

  @Transactional
  public void createAll(Player player) throws DataAccessException {
    List<Figure> figs = this.figureService.findAllFigures();
    for (Figure fig : figs) {
      PlayerFigures pf = new PlayerFigures();
      pf.setPlayer(player);
      pf.setFigure(fig);
      pf.setSuccesful(0);
      this.savePlayerFigures(pf);
    }
  }
}
