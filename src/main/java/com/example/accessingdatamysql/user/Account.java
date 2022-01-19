package com.example.accessingdatamysql.user;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.accessingdatamysql.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Account extends BaseEntity {

  public static final int MIN_SIZE = 3;
  public static final int MAX_SIZE = 50;

  @Size(min = MIN_SIZE, max = MAX_SIZE)
  @NotEmpty
  @NotNull
  protected String name;

  protected String surname;

  @NotEmpty
  @NotNull
  protected String password;

  @Size(min = 3, max = 50)
  @NotEmpty
  @NotNull
  @Column(unique = true)
  protected String email;

  @Size(min = 3, max = 50)
  @NotEmpty
  @NotNull
  @Column(unique = true)
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
