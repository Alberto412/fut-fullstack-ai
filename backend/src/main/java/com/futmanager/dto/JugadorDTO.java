package com.futmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorDTO {

    private Long id;
    private String nombre;
    private String posicion;
    private Integer edad;
    private String nacionalidad;
    private Integer media;
    private String imagenUrl;
    private EquipoResumenDTO equipo;
}
