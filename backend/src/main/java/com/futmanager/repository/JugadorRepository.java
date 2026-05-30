package com.futmanager.repository;

import com.futmanager.entity.Jugador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    List<Jugador> findByEquipoId(Long equipoId);
}
