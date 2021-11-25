package com.example.accessingdatamysql.achievement;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievement")
public class Achievement extends NamedEntity {

    @NotNull
    @NotEmpty
    @Column(name = "description")
    private String description;

    @OneToOne
    private Figure figure;

    @JsonIgnore
    @ManyToMany(mappedBy = "achievements")
    private Collection<Player> players;

    public Achievement() {
        this.name = "";
        this.description = "";
    }

    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", description='" + getDescription() + "'"
                + ", figure='" + getFigure() + "'" + "}";
    }

}
