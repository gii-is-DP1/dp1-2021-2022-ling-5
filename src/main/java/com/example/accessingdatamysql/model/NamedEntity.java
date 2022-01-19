package com.example.accessingdatamysql.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class NamedEntity extends BaseEntity {

  public static final int MIN_SIZE = 3;
  public static final int MAX_SIZE = 50;

  @Size(min = MIN_SIZE, max = MAX_SIZE)
  @NotEmpty
  protected String name;

}