package com.futmanager.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.futmanager.dto.JugadorDTO;
import com.futmanager.entity.Equipo;
import com.futmanager.entity.Jugador;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class JugadorMapperTest {

    private final JugadorMapper jugadorMapper = Mappers.getMapper(JugadorMapper.class);

    @Test
    void convierteJugadorADTOConEquipoResumen() {
        Equipo equipo = new Equipo(1L, "Real Madrid", "LaLiga", "España", "Santiago Bernabéu", "", List.of());
        Jugador jugador = new Jugador(1L, "Vinicius Junior", "EI", 24, "Brasil", 90, "", equipo, null);

        JugadorDTO dto = jugadorMapper.toDTO(jugador);

        assertThat(dto.getNombre()).isEqualTo("Vinicius Junior");
        assertThat(dto.getEquipo().getId()).isEqualTo(1L);
        assertThat(dto.getEquipo().getNombre()).isEqualTo("Real Madrid");
    }
}
