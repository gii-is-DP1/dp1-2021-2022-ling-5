package com.example.accessingdatamysql.achievement;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.model.NamedEntity;
import com.example.accessingdatamysql.user.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievement")
public class Achievement extends NamedEntity {

    @NotNull
    @NotEmpty
    @Size(max=200)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "achievementTypes")
    private AchievementTypes achievementTypes;

    @NotNull
    @Column(name = "requirement")
    private Integer requirement;

    @OneToOne
    private Figure figure;

    @JsonIgnore
    @ManyToMany(mappedBy = "achievements")
    private List<Player> players;

    public Achievement() {
        this.name = "";
        this.description = "";
    }

    public Achievement(String name, String description, String achievementType, Integer requirement) {
        this.name = name;
        this.description = description;
        this.achievementTypes = AchievementTypes.valueOf(achievementType);
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", description='" + getDescription() + "'"
                + ", figure='" + getFigure() + "'" + ", type='"+ getAchievementTypes().value() + "'" +
                ", requirements='"+ getRequirement().toString() + "'" +"}";
    }

}
