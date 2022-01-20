package com.example.accessingdatamysql.minigame;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.example.accessingdatamysql.game.Game;
import com.example.accessingdatamysql.model.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Minigame extends NamedEntity {

    private String description;

    @JsonIgnore
    @ManyToMany
    private List<Game> games;

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
