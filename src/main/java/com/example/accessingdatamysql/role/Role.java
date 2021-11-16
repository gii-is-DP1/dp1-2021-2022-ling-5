package com.example.accessingdatamysql.role;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.privilege.Privilege;
import com.example.accessingdatamysql.user.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends NamedEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Collection<Account> accounts;

    @JsonIgnore
    @ManyToMany
    private Collection<Privilege> privileges;

}
