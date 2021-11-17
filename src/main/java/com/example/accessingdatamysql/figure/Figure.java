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
import com.example.accessingdatamysql.user.Account;
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
    private Collection<Account> accounts;

    @JsonIgnore
    @OneToOne(mappedBy = "figure", cascade = CascadeType.REMOVE)
    private Achievement achievement;

    @JsonIgnore
    @ManyToMany
    private Collection<Card> cards;

}
