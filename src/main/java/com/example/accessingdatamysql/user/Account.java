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
  protected String surname;

  @NotEmpty
  @NotNull
  @Column(name = "password")
  protected String password;

  @Size(min = 3, max = 50)
  @NotEmpty
  @NotNull
  @Column(name = "email", unique = true)
  protected String email;

  @Size(min = 3, max = 50)
  @NotEmpty
  @NotNull
  @Column(name = "nickname", unique = true)
  protected String nickname;

  @Override
  public String toString() {
    return ("{" +
        " id='" +
        getId() +
        "'" +
        ", name='" +
        getName() +
        "'" +
        ", surname='" +
        getSurname() +
        "'" +
        ", password='" +
        getPassword() +
        "'" +
        ", email='" +
        getEmail() +
        "'" +
        ", nickname='" +
        getNickname() +
        "'" +
        "}");
  }
}
