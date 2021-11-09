package com.example.accessingdatamysql.game;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.minigame.Minigame;
import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.result.Result;
import com.example.accessingdatamysql.user.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game extends NamedEntity {

    @Column(name = "state")
    private State state;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @ManyToMany(mappedBy = "games")
    private Collection<Minigame> minigames;

    @OneToMany(mappedBy = "game")
    private Collection<Result> results;

    @ManyToMany(mappedBy = "gamesPlayed")
    @Size(min = 2, max = 8)
    private Collection<Player> players;
}
