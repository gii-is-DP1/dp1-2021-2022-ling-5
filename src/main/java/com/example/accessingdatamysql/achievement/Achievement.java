package com.example.accessingdatamysql.achievement;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.user.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievement")
public class Achievement extends NamedEntity {

    @Column(name = "description")
    private String description;

    @OneToOne
    private Figure figure;

    @ManyToMany(mappedBy = "achievements")
    private Collection<Player> players;

}
