package com.example.accessingdatamysql.statistics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ranking {
    private String imageName;
    private String nickname;
    private Integer points;

    public Ranking(String imageName, String nickname, Integer points){
        this.imageName = imageName;
        this.nickname = nickname;
        this.points = points;
    }
}
