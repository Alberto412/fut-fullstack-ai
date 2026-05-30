package com.futmanager.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.futmanager.dto.CartaFUTDTO;
import com.futmanager.entity.CartaFUT;
import com.futmanager.entity.Jugador;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CartaFUTMapperTest {

    private final CartaFUTMapper cartaFUTMapper = Mappers.getMapper(CartaFUTMapper.class);

    @Test
    void convierteCartaADTOConJugadorResumen() {
        Jugador jugador = new Jugador(1L, "Lamine Yamal", "ED", 17, "España", 84, "", null, null);
        CartaFUT carta = new CartaFUT(1L, "Promesa", 88, 78, 80, 86, 30, 60, "", jugador);

        CartaFUTDTO dto = cartaFUTMapper.toDTO(carta);

        assertThat(dto.getTipoCarta()).isEqualTo("Promesa");
        assertThat(dto.getJugador().getId()).isEqualTo(1L);
        assertThat(dto.getJugador().getNombre()).isEqualTo("Lamine Yamal");
    }
}
