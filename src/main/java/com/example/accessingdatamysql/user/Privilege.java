package com.example.accessingdatamysql.user;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.example.accessingdatamysql.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Privilege extends NamedEntity {

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}