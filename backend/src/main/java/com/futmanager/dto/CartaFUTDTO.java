package com.futmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaFUTDTO {

    private Long id;
    private String tipoCarta;
    private Integer ritmo;
    private Integer tiro;
    private Integer pase;
    private Integer regate;
    private Integer defensa;
    private Integer fisico;
    private String imagenUrl;
    private JugadorResumenDTO jugador;
}
