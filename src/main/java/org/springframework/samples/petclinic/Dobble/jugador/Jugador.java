package org.springframework.samples.petclinic.Dobble.jugador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jugadores",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"nombredeUsuario"}
    ))
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
public class Jugador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Column(name = "nombredeUsuario")
    private String nombredeUsuario;

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String contraseña;
}
