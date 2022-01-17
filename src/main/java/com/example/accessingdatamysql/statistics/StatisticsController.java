package com.example.accessingdatamysql.statistics;

import com.example.accessingdatamysql.figure.Figure;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
  public @ResponseBody List<Integer> getPointsByMinigames(
    @PathVariable Long playerId
  ) {
    return this.statisticsService.pointsByMinigames(playerId);
  }

  @GetMapping(value = "/statistics/maxminfigure/{playerId}")
  public @ResponseBody List<Figure> getMaxMinFigures(
    @PathVariable Long playerId
  ) {
    return this.statisticsService.maxMinFigures(playerId);
  }

  @GetMapping(value = "/statistics/top10ranking")
  public @ResponseBody List<Ranking> getTop10() {
    return this.statisticsService.getTop10Ranking();
  }

  @GetMapping(value = "/statistics/ranking/{playerId}")
  public @ResponseBody Pair<Integer, Ranking> getPositionRanking(
    @PathVariable Long playerId
  ) {
    return this.statisticsService.getPositionRanking(playerId);
  }

  @GetMapping(value = "/statistics/playerpergame/{playerId}")
  public @ResponseBody Map<Integer, Double> getFrecuencyPlayers(
    @PathVariable Long playerId
  ) {
    return this.statisticsService.getFrecuenciaJugadores(playerId);
  }

  @GetMapping(value = "/statistics/propTotal/{playerId}")
  public @ResponseBody Double getPropTotal(@PathVariable Long playerId) {
    return this.statisticsService.propTotal(playerId);
  }

  @GetMapping(value = "/statistics/maxMinAvgAll")
  public @ResponseBody Map<String, Double> getMaxMinAvgAll() {
    return this.statisticsService.maxMinAvgAll();
  }

  @GetMapping(value = "/statistics/maxMinAvg/{playerId}")
  public @ResponseBody Map<String, Double> getMaxMinAvg(
    @PathVariable Long playerId
  ) {
    return this.statisticsService.maxMinAvg(playerId);
  }

  @GetMapping(value = "/statistics/propTotalTime/{playerId}")
  public @ResponseBody Double getPropTotalTime(@PathVariable Long playerId) {
    return this.statisticsService.propTiempo(playerId);
  }

  @GetMapping(value = "/statistics/maxMinAvgTime/{playerId}")
  public @ResponseBody Map<String, Long> getMaxMinAvgTime(
    @PathVariable Long playerId
  ) {
    return this.statisticsService.maxMinAvgTime(playerId);
  }

  @GetMapping(value = "/statistics/maxMinAvgTimeAll")
  public @ResponseBody Map<String, Long> getMaxMinAvgTimeAll() {
    return this.statisticsService.maxMinAvgTimeAll();
  }
}
