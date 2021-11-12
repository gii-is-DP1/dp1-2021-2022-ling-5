package com.example.accessingdatamysql.user;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.figure.Figure;
import com.example.accessingdatamysql.modification.Modification;
import com.example.accessingdatamysql.role.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @MappedSuperclass
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Size(min = 3, max = 50)
    @NotEmpty
    @Column(name = "name")
    protected String name;

    @Column(name = "surname")
    private String surname;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "nickname")
    private String nickname;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "account")
    private Collection<Modification> changes;

    @ManyToOne
    private Figure figure;

}
