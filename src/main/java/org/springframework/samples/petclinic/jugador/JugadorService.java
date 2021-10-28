package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JugadorService {
    private JugadorRepository jugadorRepository;

    @Autowired
    public JugadorService(JugadorRepository jugadorRepository){
        this.jugadorRepository = jugadorRepository;
    }

    @Transactional
    public List<Jugador> findJugadorByName(String nombredeUsuario) throws DataAccessException{
        return jugadorRepository.findByName(nombredeUsuario);
    }

    @Transactional
    public Jugador findJugadorById(int id) throws DataAccessException{
        return jugadorRepository.findById(id);
    }

    @Transactional
    public void saveJugador(Jugador jugador) throws DataAccessException{
        jugadorRepository.save(jugador);
    }
}
