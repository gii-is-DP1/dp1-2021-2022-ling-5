package com.example.accessingdatamysql.role;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.user.Admin;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends NamedEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Player> players;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Admin> admins;

    public Role() {
        this.name = "";
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + "'"
                + "}";
    }

}
