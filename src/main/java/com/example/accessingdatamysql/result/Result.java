package com.example.accessingdatamysql.result;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Result extends BaseEntity {

    @NotNull
    @NotEmpty
    private String data;

    @NotNull
    private Integer totalPoints;

    @ManyToOne
    private Game game;

    @JsonIgnore
    @ManyToOne
    private Player player;

    public Result() {
        this.data = "";
        this.totalPoints = 0;
    }

    public Result(String data, Integer totalPoints) {
        this.data = data;
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", data='" + getData() + "'" + ", totalPoints='" + getTotalPoints() + "'"
                + ", game='" + getGame() + "'" + "}";
    }

}
