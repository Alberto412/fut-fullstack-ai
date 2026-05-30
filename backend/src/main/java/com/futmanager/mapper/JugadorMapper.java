package com.futmanager.mapper;

import com.futmanager.dto.JugadorDTO;
import com.futmanager.dto.JugadorResumenDTO;
import com.futmanager.entity.Jugador;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = EquipoMapper.class)
public interface JugadorMapper {

    JugadorDTO toDTO(Jugador jugador);

    Jugador toEntity(JugadorDTO jugadorDTO);

    JugadorResumenDTO toResumenDTO(Jugador jugador);

    Jugador toEntity(JugadorResumenDTO jugadorResumenDTO);

    List<JugadorDTO> toDTOList(List<Jugador> jugadores);
}
