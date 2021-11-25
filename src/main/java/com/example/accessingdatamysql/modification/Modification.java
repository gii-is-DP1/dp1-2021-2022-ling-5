package com.example.accessingdatamysql.modification;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.accessingdatamysql.model.BaseEntity;
import com.example.accessingdatamysql.user.Admin;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "modification")
public class Modification extends BaseEntity {

    @NotNull
    @Column(name = "moment")
    private Date moment;

    @NotNull
    @NotEmpty
    @Column(name = "what")
    private String what;

    @NotNull
    @NotEmpty
    @Column(name = "beforeModification")
    private String beforeModification;

    @NotNull
    @NotEmpty
    @Column(name = "afterModification")
    private String afterModification;

    @JsonIgnore
    @ManyToOne
    private Player player;

    @JsonIgnore
    @ManyToOne
    private Admin admin;

    public Modification() {
        this.moment = new Date(System.currentTimeMillis());
        this.what = "";
        this.beforeModification = "";
        this.afterModification = "";
    }

    public Modification(Date moment, String what, String beforeModification, String afterModification) {
        this.moment = moment;
        this.what = what;
        this.beforeModification = beforeModification;
        this.afterModification = afterModification;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", moment='" + getMoment() + "'" + ", what='" + getWhat() + "'"
                + ", beforeModification='" + getBeforeModification() + "'" + ", afterModification='"
                + getAfterModification() + "'" + "}";
    }

}
