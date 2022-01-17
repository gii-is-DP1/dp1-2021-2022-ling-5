package com.example.accessingdatamysql.card;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Card extends NamedEntity {

    @ManyToMany(mappedBy = "cards")
    @Size(max = 6)
    private List<Figure> figures;

    public Card() {
        this.name = "";
    }

    public Card(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", figures='" + getFigures() + "'" + "}";
    }

}
