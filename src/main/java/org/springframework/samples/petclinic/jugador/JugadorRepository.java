package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface JugadorRepository extends Repository<Jugador, Integer>{
    
    void save(Jugador jugador) throws DataAccessException;

    @Query("SELECT jugador FROM Jugador jugador WHERE jugador.nombredeUsuario LIKE :nombredeUsuario%")
    public List<Jugador> findByName(@Param("nombredeUsuario")String nombredeUsuario) throws DataAccessException;

    @Query("SELECT jugador FROM Jugador jugador WHERE jugador.id =: id")
    public Jugador findById(@Param("id")Integer id) throws DataAccessException;
}
