package com.futmanager.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.futmanager.dto.EquipoDTO;
import com.futmanager.entity.Equipo;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class EquipoMapperTest {

    private final EquipoMapper equipoMapper = Mappers.getMapper(EquipoMapper.class);

    @Test
    void convierteEquipoADTO() {
        Equipo equipo = new Equipo(1L, "Real Madrid", "LaLiga", "España", "Santiago Bernabéu", "", List.of());

        EquipoDTO dto = equipoMapper.toDTO(equipo);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getNombre()).isEqualTo("Real Madrid");
        assertThat(dto.getLiga()).isEqualTo("LaLiga");
    }
}
