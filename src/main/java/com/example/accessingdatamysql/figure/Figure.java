package com.example.accessingdatamysql.figure;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.accessingdatamysql.achievement.Achievement;
import com.example.accessingdatamysql.card.Card;
import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.user.Admin;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "figure")
public class Figure extends NamedEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "figure")
    private Collection<Player> players;

    @JsonIgnore
    @OneToMany(mappedBy = "figure")
    private Collection<Admin> admins;

    @JsonIgnore
    @OneToOne(mappedBy = "figure", cascade = CascadeType.REMOVE)
    private Achievement achievement;

    @JsonIgnore
    @ManyToMany
    private Collection<Card> cards;

    public Figure() {
        this.name = "";
    }

    public Figure(String name) {
        this.name = name;
    }

}
