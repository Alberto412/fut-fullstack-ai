package com.futmanager.mapper;

import com.futmanager.dto.EquipoDTO;
import com.futmanager.dto.EquipoResumenDTO;
import com.futmanager.entity.Equipo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipoMapper {

    EquipoDTO toDTO(Equipo equipo);

    Equipo toEntity(EquipoDTO equipoDTO);

    EquipoResumenDTO toResumenDTO(Equipo equipo);

    Equipo toEntity(EquipoResumenDTO equipoResumenDTO);

    List<EquipoDTO> toDTOList(List<Equipo> equipos);
}
