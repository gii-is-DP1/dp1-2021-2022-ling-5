package com.example.accessingdatamysql.game;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.minigame.Minigame;
import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game extends NamedEntity {

    // @Size(min = 3, max = 50)
    // @NotEmpty
    // @Column(name = "name", unique = true)
    // private String name;

    @Column(name = "state")
    private State state;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @NotNull
    @Column(name = "creator")
    private Integer creator;

    @Column(name = "winner")
    private Integer winner;

    @ManyToMany(mappedBy = "games")
    @Size(max = 3)
    private Collection<Minigame> minigames;

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)
    private Collection<Result> results;

    @JsonIgnore
    @ManyToMany(mappedBy = "gamesPlayed")
    @Size(max = 8)
    private Collection<Player> players;

    public Game() {
        this.name = "";
        this.state = State.UNSTARTED;
        this.startTime = new Date(System.currentTimeMillis());
        this.endTime = new Date(System.currentTimeMillis());
        this.creator = 0;
        this.winner = 0;
    }

    public Game(String name, State state, Date startTime, Date endTime, Integer creator, Integer winner) {
        this.name = name;
        this.state = state;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creator = creator;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", state='" + getState() + "'"
                + ", startTime='" + getStartTime() + "'" + ", endTime='" + getEndTime() + "'" + ", creator='"
                + getCreator() + "'" + ", winner='" + getWinner() + "'" + ", minigames='" + getMinigames() + "'" + "}";
    }

}
