package com.example.accessingdatamysql.user;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @Entity
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50)
    @NotEmpty
    @NotNull
    @Column(name = "name")
    protected String name;

    @Column(name = "surname")
    private String surname;

    @NotEmpty
    @NotNull
    @Column(name = "password")
    private String password;

    @NotEmpty
    @NotNull
    @Column(name = "email")
    private String email;

    @NotEmpty
    @NotNull
    @Column(name = "nickname")
    private String nickname;

    // @ManyToOne
    // private Role role;

    // @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    // private Collection<Modification> modifications;

    // @ManyToOne
    // private Figure figure;

}
