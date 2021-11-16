package com.example.accessingdatamysql.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "result")
public class Result extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(name = "data")
    private String data;

    @JsonIgnore
    @ManyToOne
    private Game game;

    @JsonIgnore
    @ManyToOne
    private Player player;

}
