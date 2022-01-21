package com.example.accessingdatamysql.user;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.role.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends Account {

    @ManyToOne
    private Role role;

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
        return "{" + super.toString() + ", role='" + getRole() + "'"
                + ", figure='" + getFigure() + "'" + "}";
    }

}