package com.example.accessingdatamysql.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameService;
import com.example.accessingdatamysql.playerfigures.PlayerFigures;
import com.example.accessingdatamysql.playerfigures.PlayerFiguresService;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.result.ResultController;
import com.example.accessingdatamysql.result.ResultService;
import com.example.accessingdatamysql.user.Player;
import com.example.accessingdatamysql.user.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public List<Ranking> getTop10Ranking(){
        Map<Player, Integer> map1 = new HashMap<Player, Integer>();
        for(Player p: playerService.findAllPlayers()){
            Integer points = pointsByMinigames(p.getId()).stream().collect(Collectors.summingInt(Integer::intValue));
            map1.put(p, points);
        }
        Comparator<Ranking> comparator = (Ranking r1, Ranking r2) -> r1.getPoints().compareTo(r2.getPoints());
        List<Ranking> result = map1.entrySet().stream().map(e->new Ranking(e.getKey().getFigure().getName(),
            e.getKey().getNickname(), e.getValue())).sorted(comparator).limit(10).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

    public Pair<Integer, Ranking> getPositionRanking(Long playerId){
        Map<Player, Integer> map1 = new HashMap<Player, Integer>();
        for(Player p: playerService.findAllPlayers()){
            Integer points = pointsByMinigames(p.getId()).stream().collect(Collectors.summingInt(Integer::intValue));
            map1.put(p, points);
        }
        Comparator<Ranking> comparator = (Ranking r1, Ranking r2) -> r1.getPoints().compareTo(r2.getPoints());
        List<Ranking> ranking = map1.entrySet().stream().map(e->new Ranking(e.getKey().getFigure().getName(),
            e.getKey().getNickname(), e.getValue())).sorted(comparator).collect(Collectors.toList());
        Collections.reverse(ranking);
        Player player = playerService.findPlayer(playerId).get();
        Ranking rank = ranking.stream().filter(r->r.getNickname().equals(player.getNickname())).findAny().get();
        Integer position = ranking.indexOf(rank);
        return Pair.of(position+1, rank);
    }

    public Map<Integer, Double> getFrecuenciaJugadores(Long playerId){
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
        for(Game g: playerGames){
            Integer numberPlayer = g.getPlayers().size();
            Double n = total.get(numberPlayer);
            total.put(numberPlayer, n+1.0);
        }
        total.entrySet().stream().forEach(e->result.put(e.getKey(), (e.getValue()/playerGames.size())*100));
        return result;
    }

}