package com.example.accessingdatamysql.card;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Card extends BaseEntity {

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "cards")
    @Size(min = 6, max = 6)
    private Collection<Figure> figures;
}
