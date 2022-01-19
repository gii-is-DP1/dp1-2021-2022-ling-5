package com.example.accessingdatamysql.statistics;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameService;
import com.example.accessingdatamysql.playerfigures.PlayerFigures;
import com.example.accessingdatamysql.playerfigures.PlayerFiguresService;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.result.ResultService;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

  @Autowired
  private ResultService resultService;

  @Autowired
  private PlayerFiguresService playerFiguresService;

  @Autowired
  private PlayerService playerService;

  @Autowired
  private GameService gameService;

  public List<Integer> pointsByMinigames(Long id) {
    List<Result> results = resultService.findAllResultsByPlayer(id);
    List<Integer> res = new ArrayList<>();
    Integer minigame1 = 0;
    Integer minigame2 = 0;
    Integer minigame3 = 0;
    Integer total = 0;
    for (Result r : results) {
      total += r.getTotalPoints();
      String[] trozos = r.getData().strip().split(" ");
      if (trozos.length > 1) {
        minigame1 += Integer.parseInt(trozos[0]);
        minigame2 += Integer.parseInt(trozos[1]);
        minigame3 += Integer.parseInt(trozos[2]);
      } else {
        String name = r.getGame().getMinigames().get(0).getName();
        if (name == "Torre Infernal") {
          minigame1 += Integer.parseInt(trozos[0]);
        } else if (name == "Foso") {
          minigame2 += Integer.parseInt(trozos[0]);
        } else {
          minigame3 += Integer.parseInt(trozos[0]);
        }
      }
    }
    res.add(total);
    res.add(minigame1);
    res.add(minigame2);
    res.add(minigame3);
    return res;
  }

  public List<Figure> maxMinFigures(Long id) {
    List<Figure> res = new ArrayList<>();
    List<PlayerFigures> pfs = this.playerFiguresService.findAllPlayerFiguresByPlayer(id);
    PlayerFigures max = pfs.get(0);
    PlayerFigures min = pfs.get(1);
    for (PlayerFigures pf : pfs) {
      if (pf.getSuccesful() > max.getSuccesful()) {
        max = pf;
      } else if (pf.getSuccesful() < min.getSuccesful()) {
        min = pf;
      }
    }
    res.add(max.getFigure());
    res.add(min.getFigure());
    return res;
  }

  public List<Ranking> getTop10Ranking() {
    Map<Player, Integer> map1 = new HashMap<Player, Integer>();
    for (Player p : playerService.findAllPlayers()) {
      Integer points = pointsByMinigames(p.getId()).stream().collect(Collectors.summingInt(Integer::intValue));
      map1.put(p, points);
    }
    List<Ranking> result = map1.entrySet().stream().map(e -> new Ranking(e.getKey().getFigure().getName(),
        e.getKey().getNickname(), e.getValue())).sorted(new RankingComparable())
        .limit(10).collect(Collectors.toList());
    return result;
  }

  public Pair<Integer, Ranking> getPositionRanking(Long playerId) {
    Map<Player, Integer> map1 = new HashMap<Player, Integer>();
    for (Player p : playerService.findAllPlayers()) {
      Integer points = pointsByMinigames(p.getId()).stream().collect(Collectors.summingInt(Integer::intValue));
      map1.put(p, points);
    }
    List<Ranking> ranking = map1.entrySet().stream().map(e -> new Ranking(e.getKey().getFigure().getName(),
        e.getKey().getNickname(), e.getValue())).sorted(new RankingComparable()).collect(Collectors.toList());
    Player player = playerService.findPlayer(playerId).get();
    Ranking rank = ranking.stream().filter(r -> r.getNickname().equals(player.getNickname())).findAny().get();
    Integer position = ranking.indexOf(rank);
    return Pair.of(position + 1, rank);
  }

  public Map<Integer, Double> getFrecuenciaJugadores(Long playerId) {
    Map<Integer, Double> result = new HashMap<Integer, Double>();
    List<Game> playerGames = gameService.getGamesByPlayer(playerId);
    Map<Integer, Double> total = new HashMap<Integer, Double>();
    total.put(2, 0.0);
    total.put(3, 0.0);
    total.put(4, 0.0);
    total.put(5, 0.0);
    total.put(6, 0.0);
    total.put(7, 0.0);
    total.put(8, 0.0);
    for (Game g : playerGames) {
      Integer numberPlayer = g.getPlayers().size();
      Double n = total.get(numberPlayer);
      total.put(numberPlayer, n + 1.0);
    }
    if (playerGames.size() == 0) {
      return total;
    }
    total.entrySet().stream().forEach(e -> result.put(e.getKey(), (e.getValue() / playerGames.size()) * 100));
    return result;
  }

  public Double propTotal(Long id) {

    List<Game> partidas = gameService.findAllGames();
    List<Result> misPartidas = resultService.findAllResultsByPlayer(id);
    Double a = (double) partidas.size();
    Double b = (double) misPartidas.size();
    if (a == 0)
      return 0.;
    Double result = b / a;
    return result;

  }

  public Double avg(List<Integer> m) {
    Integer sum = 0;
    for (int i = 0; i < m.size(); i++) {
      sum = sum + m.get(i);
    }
    return (double) sum / m.size();
  }

  public Map<String, Double> maxMinAvg(Long id) {
    List<Result> resultados = resultService.findAllResultsByPlayer(id);
    List<Integer> misPuntos = new ArrayList<Integer>();
    for (Integer i = 0; i < resultados.size(); i++) {
      Integer result = resultados.get(i).getTotalPoints();
      misPuntos.add(result);
    }
    Collections.sort(misPuntos);
    Map<String, Double> map = new HashMap<>();
    map.put("min", misPuntos.size() > 0 ? (double) misPuntos.get(0) : 0.);
    map.put("avg", misPuntos.size() > 0 ? avg(misPuntos) : 0.);
    map.put("max", misPuntos.size() > 0 ? (double) misPuntos.get(misPuntos.size() - 1) : 0.);

    return map;

  }

  public Map<String, Double> maxMinAvgAll() {
    List<Result> resultados = resultService.findAllResults();
    List<Integer> puntosAll = new ArrayList<Integer>();
    for (Integer i = 0; i < resultados.size(); i++) {
      Integer result = resultados.get(i).getTotalPoints();
      puntosAll.add(result);
    }
    Collections.sort(puntosAll);
    Map<String, Double> map = new HashMap<>();
    map.put("min", puntosAll.size() > 0 ? (double) puntosAll.get(0) : 0);
    map.put("avg", puntosAll.size() > 0 ? avg(puntosAll) : 0);
    map.put("max", puntosAll.size() > 0 ? (double) puntosAll.get(puntosAll.size() - 1) : 0);

    return map;

  }

  public Double propTiempo(Long playerId) {
    List<Game> partidas = gameService.findAllGames();
    List<Game> misPartidas = gameService.getGamesByPlayer(playerId);
    List<Double> l1 = new ArrayList<Double>();
    List<Double> l2 = new ArrayList<Double>();
    for (int i = 0; i < partidas.size(); i++) {
      Date f1 = partidas.get(i).getStartTime();
      Date f2 = partidas.get(i).getEndTime();
      Long m = f2.getTime() - f1.getTime();
      Double res = Double.longBitsToDouble(m);
      l1.add(res);

    }
    for (int i = 0; i < misPartidas.size(); i++) {
      Date f1 = misPartidas.get(i).getStartTime();
      Date f2 = misPartidas.get(i).getEndTime();
      Long m = f2.getTime() - f1.getTime();
      Double res = Double.longBitsToDouble(m);
      l2.add(res);

    }

    Double divisor = l1.stream().mapToDouble(Double::doubleValue).sum();
    if (divisor == 0 || l1.size() == 0) {
      return 0.0;
    } else {
      return l2.stream().mapToDouble(Double::doubleValue).sum() / l1.stream().mapToDouble(Double::doubleValue).sum();

    }
  }

  public Map<String, Long> maxMinAvgTime(Long playerId) {

    List<Game> misPartidas = gameService.getGamesByPlayer(playerId);
    List<Long> l2 = new ArrayList<Long>();
    Long min = 0L;
    Long max = 0L;
    Long avg = 0L;

    for (int i = 0; i < misPartidas.size(); i++) {
      Date f1 = misPartidas.get(i).getStartTime();
      Date f2 = misPartidas.get(i).getEndTime();
      TimeUnit time = TimeUnit.MINUTES;
      Long m = f2.getTime() - f1.getTime();
      long diffrence = time.convert(m, TimeUnit.MILLISECONDS);

      l2.add(diffrence);

    }

    Collections.sort(l2);
    if (l2.size() > 0) {
      min = l2.get(0);
      max = l2.get(l2.size() - 1);
      avg = l2.stream().mapToLong(Long::longValue).sum() / l2.size();
    }

    Map<String, Long> res = new HashMap<String, Long>();
    res.put("min", min);
    res.put("avg", avg);
    res.put("max", max);

    return res;
  }

  public Map<String, Long> maxMinAvgTimeAll() {

    List<Game> misPartidas = gameService.findAllGames();
    List<Long> l2 = new ArrayList<Long>();
    Long min = 0L;
    Long max = 0L;
    Long avg = 0L;

    for (int i = 0; i < misPartidas.size(); i++) {
      Date f1 = misPartidas.get(i).getStartTime();
      Date f2 = misPartidas.get(i).getEndTime();
      TimeUnit time = TimeUnit.MINUTES;
      Long m = f2.getTime() - f1.getTime();
      long diffrence = time.convert(m, TimeUnit.MILLISECONDS);

      l2.add(diffrence);

    }

    Collections.sort(l2);
    if (l2.size() > 0) {
      min = l2.get(0);
      max = l2.get(l2.size() - 1);
      avg = l2.stream().mapToLong(Long::longValue).sum() / l2.size();
    }

    Map<String, Long> res = new HashMap<String, Long>();
    res.put("min", min);
    res.put("avg", avg);
    res.put("max", max);

    return res;
  }

}
