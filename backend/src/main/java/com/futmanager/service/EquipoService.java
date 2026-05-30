package com.futmanager.service;

import com.futmanager.entity.Equipo;
import com.futmanager.entity.Jugador;
import com.futmanager.repository.EquipoRepository;
import com.futmanager.repository.JugadorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;

    public EquipoService(EquipoRepository equipoRepository, JugadorRepository jugadorRepository) {
        this.equipoRepository = equipoRepository;
        this.jugadorRepository = jugadorRepository;
    }

    public List<Equipo> listar() {
        return equipoRepository.findAll();
    }

    public Equipo obtenerPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

    public Equipo crear(Equipo equipo) {
        equipo.setId(null);
        return equipoRepository.save(equipo);
    }

    public Equipo actualizar(Long id, Equipo datos) {
        Equipo equipo = obtenerPorId(id);
        equipo.setNombre(datos.getNombre());
        equipo.setLiga(datos.getLiga());
        equipo.setPais(datos.getPais());
        equipo.setEstadio(datos.getEstadio());
        equipo.setEscudoUrl(datos.getEscudoUrl());
        return equipoRepository.save(equipo);
    }

    public void eliminar(Long id) {
        Equipo equipo = obtenerPorId(id);
        List<Jugador> jugadores = jugadorRepository.findByEquipoId(id);
        jugadores.forEach(jugador -> jugador.setEquipo(null));
        jugadorRepository.saveAll(jugadores);
        equipoRepository.delete(equipo);
    }
}
