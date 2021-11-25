package com.example.accessingdatamysql.minigame;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.model.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "minigame")
public class Minigame extends NamedEntity {

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany
    private Collection<Game> games;

    public Minigame() {
        this.name = "";
        this.description = "";
    }

    public Minigame(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", description='" + getDescription() + "'"
                + "}";
    }

}
