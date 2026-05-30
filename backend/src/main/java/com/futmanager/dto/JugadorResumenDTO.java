package com.futmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorResumenDTO {

    private Long id;
    private String nombre;
    private String posicion;
    private Integer media;
}
