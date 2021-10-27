package org.springframework.samples.petclinic.Dobble.jugador;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface JugadorRepository extends Repository<Jugador, String>{
    
    void save(Jugador jugador) throws DataAccessException;

    @Query("SELECT jugador FROM jugadores WHERE jugador.nombredeUsuario =: nombredeUsuario")
    public Jugador findByName(@Param("nombredeUsuario")String nombredeUsuario);

    @Query("SELECT jugador FROM jugadores WHERE jugador.id =: id")
    public Jugador findById(@Param("id")int id);

    @Query("SELECT * FROM jugadores")
    public List<Jugador> findAll();
}
