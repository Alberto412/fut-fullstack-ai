package com.futmanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.futmanager.entity.CartaFUT;
import com.futmanager.entity.Jugador;
import com.futmanager.repository.CartaFUTRepository;
import com.futmanager.repository.JugadorRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartaFUTServiceTest {

    @Mock
    private CartaFUTRepository cartaFUTRepository;

    @Mock
    private JugadorRepository jugadorRepository;

    @InjectMocks
    private CartaFUTService cartaFUTService;

    @Test
    void listarDevuelveCartas() {
        when(cartaFUTRepository.findAll()).thenReturn(List.of(new CartaFUT(1L, "TOTY", 85, 88, 90, 92, 83, 88, "", null)));

        List<CartaFUT> cartas = cartaFUTService.listar();

        assertThat(cartas).hasSize(1);
        assertThat(cartas.get(0).getTipoCarta()).isEqualTo("TOTY");
    }

    @Test
    void crearResuelveJugadorYGuardaCarta() {
        Jugador jugador = new Jugador(1L, "Lamine Yamal", "ED", 17, "España", 84, "", null, null);
        CartaFUT entrada = new CartaFUT(null, "Promesa", 88, 78, 80, 86, 30, 60, "", jugador);
        when(jugadorRepository.findById(1L)).thenReturn(Optional.of(jugador));
        when(cartaFUTRepository.save(any(CartaFUT.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CartaFUT resultado = cartaFUTService.crear(entrada);

        assertThat(resultado.getJugador().getNombre()).isEqualTo("Lamine Yamal");
        verify(cartaFUTRepository).save(any(CartaFUT.class));
    }

    @Test
    void actualizarCambiaStats() {
        CartaFUT existente = new CartaFUT(1L, "Oro raro", 95, 84, 81, 91, 35, 75, "", null);
        CartaFUT nuevosDatos = new CartaFUT(null, "Especial", 96, 88, 84, 93, 40, 78, "", null);
        when(cartaFUTRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(cartaFUTRepository.save(any(CartaFUT.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CartaFUT resultado = cartaFUTService.actualizar(1L, nuevosDatos);

        assertThat(resultado.getTipoCarta()).isEqualTo("Especial");
        assertThat(resultado.getRitmo()).isEqualTo(96);
    }

    @Test
    void obtenerPorIdLanzaRuntimeExceptionSiNoExiste() {
        when(cartaFUTRepository.findById(77L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cartaFUTService.obtenerPorId(77L));

        assertThat(exception.getMessage()).isEqualTo("Carta FUT no encontrada");
    }

    @Test
    void eliminarBorraCarta() {
        CartaFUT cartaFUT = new CartaFUT(1L, "Oro raro", 89, 93, 70, 80, 45, 88, "", null);
        when(cartaFUTRepository.findById(1L)).thenReturn(Optional.of(cartaFUT));

        cartaFUTService.eliminar(1L);

        verify(cartaFUTRepository).delete(cartaFUT);
    }
}
