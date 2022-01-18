package com.example.accessingdatamysql.figure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FigureService {

  private FigureRepository figureRepository;

  @Autowired
  public FigureService(FigureRepository figureRepository) {
    this.figureRepository = figureRepository;
  }

  @Transactional
  public Figure saveFigure(Figure figure) throws DataAccessException {
    figureRepository.save(figure);
    return figure;
  }

  @Transactional(readOnly = true)
  public Optional<Figure> findFigure(Long id) {
    return figureRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Figure> findAllFigures() {
    return StreamSupport
        .stream(figureRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteFigure(Long id) {
    figureRepository.deleteById(id);
  }

  @Transactional
  public void deleteAllFigures() {
    figureRepository.deleteAll();
  }
}
