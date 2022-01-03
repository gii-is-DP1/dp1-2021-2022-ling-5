package com.example.accessingdatamysql.statistics;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.playerfigures.PlayerFigures;
import com.example.accessingdatamysql.playerfigures.PlayerFiguresService;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.result.ResultController;
import com.example.accessingdatamysql.result.ResultService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

  @Autowired
  private ResultService resultService;

  @Autowired
  private PlayerFiguresService playerFiguresService;

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
    List<PlayerFigures> pfs =
      this.playerFiguresService.findAllPlayerFiguresByPlayer(id);
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
}
