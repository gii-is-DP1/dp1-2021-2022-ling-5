package com.example.accessingdatamysql.figure;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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

    public Optional<Figure> findFigure(Long id) {
        return figureRepository.findById(id);
    }

    public Iterable<Figure> findAllFigures() {
        return figureRepository.findAll();
    }

    public void deleteFigure(Long id) {
        figureRepository.deleteById(id);
    }

    public void deleteAllFigures() {
        figureRepository.deleteAll();
    }
}
