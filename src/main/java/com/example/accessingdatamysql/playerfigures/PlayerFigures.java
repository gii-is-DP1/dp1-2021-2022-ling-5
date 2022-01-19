package com.example.accessingdatamysql.playerfigures;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "playerfigures")
public class PlayerFigures extends BaseEntity {

    @NotNull
    @Column(name = "succesful")
    private Integer succesful;

    @ManyToOne
    private Figure figure;

    @JsonIgnore
    @ManyToOne
    private Player player;

    public PlayerFigures(){
        this.succesful = 0;
    }

    public PlayerFigures(Integer succesful) {
        this.succesful = succesful;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", succesful='" + getSuccesful() + "'"
                + ", figure='" + getFigure() + "'" + "}";
    }

}