package com.example.accessingdatamysql.minigame;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "minigame")
public class Minigame extends NamedEntity {

    @Column(name = "description")
    private String description;

    @ManyToMany
    private Collection<Game> games;

}
