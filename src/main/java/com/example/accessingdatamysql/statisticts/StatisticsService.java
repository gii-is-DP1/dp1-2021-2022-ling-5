package com.example.accessingdatamysql.statisticts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.game.GameService;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.result.ResultService;


public class StatisticsService {

    
    public static Double propTotal(Long id){

        List<Game> partidas = GameService.findAllGames();
        List<Result> misPartidas = ResultService.findAllResultsByPlayer(id);
        Double a = (double) partidas.size();
        Double b = (double) misPartidas.size();
        Double result = b/a;
        return result;

    }

    public static Double avg(List<Integer> m){
        Integer sum = 0;
        for(int i = 0; i < m.size(); i++) { 
            sum=  sum+m.get(i);       
        }
        return (double) sum/m.size();
    }

    public static Map<String,Double> maxMinAvg(Long id){
        List<Result> resultados = ResultService.findAllResultsByPlayer(id);
        List<Integer> misPuntos = new ArrayList<Integer>();
        for(Integer i = 0; i<resultados.size(); i++){
            Integer result = resultados.get(i).getTotalPoints();
            misPuntos.add(result);
        }
        Collections.sort(misPuntos);
        Map<String,Double> map= new HashMap<>();
        map.put("min", (double)misPuntos.get(0));
        map.put("avg", avg(misPuntos));
        map.put("max",(double) misPuntos.get(misPuntos.size()-1));

        return map;


    }

    public static Map<String,Double> maxMinAvgAll(){
        List<Result> resultados = ResultService.findAllResults();
        List<Integer> puntosAll = new ArrayList<Integer>();
        for(Integer i = 0; i<resultados.size(); i++){
            Integer result = resultados.get(i).getTotalPoints();
            puntosAll.add(result);
        }
        Collections.sort(puntosAll);
        Map<String,Double> map= new HashMap<>();
        map.put("min", (double)puntosAll.get(0));
        map.put("avg", avg(puntosAll));
        map.put("max",(double) puntosAll.get(puntosAll.size()-1));

        return map;


    }

    public static Double propTiempo(Long id){
        List<Game> misPartidas = ResultService.findAllResultsByPlayer(id).stream().map(x->x.getGame()).collect(Collectors.toList());
        List<Game> partidas = GameService.findAllGames();
        
    }

    
    
}
