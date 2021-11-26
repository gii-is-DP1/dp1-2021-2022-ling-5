package com.example.accessingdatamysql.privilege;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Privilege extends NamedEntity {

    @JsonIgnore
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    public Privilege() {
        this.name = "";
    }

    public Privilege(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", roles='" + getRoles() + "'" + "}";
    }

}