package com.futmanager.service;

import com.futmanager.entity.CartaFUT;
import com.futmanager.entity.Equipo;
import com.futmanager.entity.Jugador;
import com.futmanager.repository.CartaFUTRepository;
import com.futmanager.repository.EquipoRepository;
import com.futmanager.repository.JugadorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    private final CartaFUTRepository cartaFUTRepository;

    public JugadorService(
            JugadorRepository jugadorRepository,
            EquipoRepository equipoRepository,
            CartaFUTRepository cartaFUTRepository
    ) {
        this.jugadorRepository = jugadorRepository;
        this.equipoRepository = equipoRepository;
        this.cartaFUTRepository = cartaFUTRepository;
    }

    public List<Jugador> listar() {
        return jugadorRepository.findAll();
    }

    public Jugador obtenerPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    public Jugador crear(Jugador jugador) {
        jugador.setId(null);
        jugador.setEquipo(resolverEquipo(jugador.getEquipo()));
        return jugadorRepository.save(jugador);
    }

    public Jugador actualizar(Long id, Jugador datos) {
        Jugador jugador = obtenerPorId(id);
        jugador.setNombre(datos.getNombre());
        jugador.setPosicion(datos.getPosicion());
        jugador.setEdad(datos.getEdad());
        jugador.setNacionalidad(datos.getNacionalidad());
        jugador.setMedia(datos.getMedia());
        jugador.setImagenUrl(datos.getImagenUrl());
        jugador.setEquipo(resolverEquipo(datos.getEquipo()));
        return jugadorRepository.save(jugador);
    }

    public void eliminar(Long id) {
        Jugador jugador = obtenerPorId(id);
        cartaFUTRepository.findByJugadorId(id).ifPresent(cartaFUTRepository::delete);
        jugadorRepository.delete(jugador);
    }

    private Equipo resolverEquipo(Equipo equipo) {
        if (equipo == null || equipo.getId() == null) {
            return null;
        }
        return equipoRepository.findById(equipo.getId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }
}
