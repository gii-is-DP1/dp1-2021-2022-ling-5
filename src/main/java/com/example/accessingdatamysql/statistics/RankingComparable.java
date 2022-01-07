package com.example.accessingdatamysql.statistics;

import java.util.Comparator;

public class RankingComparable implements Comparator<Ranking>{

    @Override
    public int compare(Ranking r1, Ranking r2) {
        int pointsComparator = r2.getPoints().compareTo(r1.getPoints());
        if(pointsComparator==0){
            return r1.getNickname().compareTo(r2.getNickname());
        } else{
            return pointsComparator;
        }
    }
    
}
