package com.example.accessingdatamysql.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "result")
public class Result extends BaseEntity {

    @Column(name = "data")
    private String data;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Player player;

}
