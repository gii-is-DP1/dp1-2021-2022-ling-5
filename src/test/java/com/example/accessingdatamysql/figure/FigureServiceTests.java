package com.example.accessingdatamysql.figure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FigureServiceTests {
    @Autowired
    protected FigureService figureService;

    @Test
    @Transactional
    public void shouldInsertFigure() {
        List<Figure> figures = this.figureService.findAllFigures();
        int found = figures.size();

        Figure figure = new Figure("ajedrez");
        this.figureService.saveFigure(figure);
        assertNotEquals(figure.getId(), 0L);

        figures = this.figureService.findAllFigures();
        assertEquals(figures.size(), found + 1);
    }

    @Test
    public void shouldFindSingleFigure() {
        Optional<Figure> figureOpt = this.figureService.findFigure(1L);
        if (figureOpt.isPresent()) {
            Figure figure = figureOpt.get();
            assertEquals(figure.getName(), "ajedrez");
        }
    }

    @Test
    @Transactional
    void shouldUpdateFigure() {
        Optional<Figure> figure = this.figureService.findFigure(1L);
        if (figure.isPresent()) {
            String oldName = figure.get().getName();
            String newName = oldName + "X";

            figure.get().setName(newName);
            this.figureService.saveFigure(figure.get());

            figure = this.figureService.findFigure(1L);
            if (figure.isPresent()) {
                assertEquals(figure.get().getName(), newName);
            }
        }
    }

    @Test
    void shouldDeleteFigure() {
        Figure figure = new Figure("patata");
        figure = this.figureService.saveFigure(figure);
        List<Figure> figures = this.figureService.findAllFigures();
        int found = figures.size();

        this.figureService.deleteFigure(figure.getId());
        figures = this.figureService.findAllFigures();
        assertEquals(figures.size(), found - 1);

    }

}
