package com.example.accessingdatamysql.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.modification.Modification;
import com.example.accessingdatamysql.role.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Admin") // This tells Hibernate to make a table out of this class
public class Admin extends Account {

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.REMOVE)
    private List<Modification> modifications;

    @ManyToOne
    private Figure figure;

    public Admin() {
        this.name = "";
        this.surname = "";
        this.password = "";
        this.email = "";
        this.nickname = "";
    }

    public Admin(String name, String surname, String password, String email, String nickname) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "{" + super.toString() + ", role='" + getRole() + "'" + ", modifications='" + getModifications() + "'"
                + ", figure='" + getFigure() + "'" + "}";
    }

}