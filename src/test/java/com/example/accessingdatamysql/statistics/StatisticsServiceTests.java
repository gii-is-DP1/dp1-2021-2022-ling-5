package com.example.accessingdatamysql.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.figure.FigureRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StatisticsServiceTests {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private FigureRepository figureRepository;

    @Test
    void testPointsByMinigames() {

        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(30);
        l1.add(10);
        l1.add(10);
        l1.add(10);
        assertEquals(statisticsService.pointsByMinigames(1L), l1);
    }

    @Test
    void testMaxMinFigures() {

        List<Figure> res = new ArrayList<Figure>();
        Figure f1 = figureRepository.findById(17L).get();
        Figure f2 = figureRepository.findById(52L).get();
        res.add(f1);
        res.add(f2);
        assertEquals(statisticsService.maxMinFigures(2L), res);

    }

    @Test
    void testRanking() {

        List<Ranking> res = new ArrayList<Ranking>();
        Ranking r1 = statisticsService.getPositionRanking(1L).getSecond();
        Ranking r2 = statisticsService.getPositionRanking(2L).getSecond();
        Ranking r3 = statisticsService.getPositionRanking(3L).getSecond();
        Ranking r4 = statisticsService.getPositionRanking(4L).getSecond();
        Ranking r5 = statisticsService.getPositionRanking(5L).getSecond();
        Ranking r6 = statisticsService.getPositionRanking(6L).getSecond();
        res.add(r2);
        res.add(r1);
        res.add(r3);
        res.add(r5);
        res.add(r4);
        res.add(r6);

    }

    @Test
    void testpropjugadores() {

        Map<Integer, Double> res = new HashMap<Integer, Double>();
        res.put(2, 0.0);
        res.put(3, 50.0);
        res.put(4, 0.0);
        res.put(5, 0.0);
        res.put(6, 50.0);
        res.put(7, 0.0);
        res.put(8, 0.0);

        assertEquals(statisticsService.getFrecuenciaJugadores(1L), res);
    }

    @Test
    void testMaxMinAvgAll() {
        Map<String, Double> res = new HashMap<String, Double>();
        res.put("min", 0.0);
        res.put("avg",8.571428571428571);
        res.put("max", 30.0);
        assertEquals(statisticsService.maxMinAvgAll(), res);

    }

    @Test
    void testPropTotal() {
        assertEquals(statisticsService.propTotal(1L), 0.5);
    }

    @Test
    void testMaxMinAvgPlayer() {

        Map<String, Double> res = new HashMap<String, Double>();
        res.put("min", 10.0);
        res.put("avg", 15.0);
        res.put("max", 20.0);
        System.out.println("HOLAAAAAAAAA");
        System.out.println("PRUEBAAAAAAAAAAAAAAA"+statisticsService.maxMinAvg(1L));

        assertEquals(statisticsService.maxMinAvg(1L), res);

    }

    @Test
    void testMaxMinAvgTimePlayer() {

        Map<String, Long> res = new HashMap<String, Long>();
        res.put("min", 10L);
        res.put("avg", 25L);
        res.put("max", 40L);
        assertEquals(statisticsService.maxMinAvgTime(1L), res);

    }

    @Test
    void testMaxMinAvgTimeAll() {

        Map<String, Long> res = new HashMap<String, Long>();
        res.put("min", 10L);
        res.put("avg", 25L);
        res.put("max", 40L);
        assertEquals(statisticsService.maxMinAvgTimeAll(), res);

    }

}
