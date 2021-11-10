package com.example.accessingdatamysql.user;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.example.accessingdatamysql.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends NamedEntity {

    @OneToMany(mappedBy = "role")
    private Collection<Account> accounts;

    @ManyToMany
    private Collection<Privilege> privileges;

}
