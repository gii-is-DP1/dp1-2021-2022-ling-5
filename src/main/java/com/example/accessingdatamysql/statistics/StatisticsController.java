package com.example.accessingdatamysql.statistics;

import java.util.List;

import com.example.accessingdatamysql.figure.Figure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping(value = "/statistics/pointsbyminigames/{playerId}")
    public @ResponseBody List<Integer> getPointsByMinigames(@PathVariable Long playerId) {
        return this.statisticsService.pointsByMinigames(playerId);
    }

    @GetMapping(value = "/statistics/maxminfigure/{playerId}")
    public @ResponseBody List<Figure> getMaxMinFigures(@PathVariable Long playerId) {
        return this.statisticsService.maxMinFigures(playerId);
    }

}
