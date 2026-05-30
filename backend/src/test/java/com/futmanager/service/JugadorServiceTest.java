package com.futmanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.futmanager.entity.Equipo;
import com.futmanager.entity.Jugador;
import com.futmanager.repository.CartaFUTRepository;
import com.futmanager.repository.EquipoRepository;
import com.futmanager.repository.JugadorRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JugadorServiceTest {

    @Mock
    private JugadorRepository jugadorRepository;

    @Mock
    private EquipoRepository equipoRepository;

    @Mock
    private CartaFUTRepository cartaFUTRepository;

    @InjectMocks
    private JugadorService jugadorService;

    @Test
    void listarDevuelveJugadores() {
        when(jugadorRepository.findAll()).thenReturn(List.of(new Jugador(1L, "Vinicius Junior", "EI", 24, "Brasil", 90, "", null, null)));

        List<Jugador> jugadores = jugadorService.listar();

        assertThat(jugadores).hasSize(1);
        assertThat(jugadores.get(0).getMedia()).isEqualTo(90);
    }

    @Test
    void crearResuelveEquipoYGuardaJugador() {
        Equipo equipo = new Equipo(1L, "Real Madrid", "LaLiga", "España", "Bernabéu", "", List.of());
        Jugador entrada = new Jugador(null, "Jude Bellingham", "MC", 21, "Inglaterra", 91, "", equipo, null);
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));
        when(jugadorRepository.save(any(Jugador.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Jugador resultado = jugadorService.crear(entrada);

        assertThat(resultado.getEquipo().getNombre()).isEqualTo("Real Madrid");
        verify(jugadorRepository).save(any(Jugador.class));
    }

    @Test
    void actualizarCambiaCamposBasicos() {
        Jugador existente = new Jugador(1L, "Vinicius", "EI", 24, "Brasil", 90, "", null, null);
        Jugador nuevosDatos = new Jugador(null, "Vinicius Junior", "EI", 25, "Brasil", 91, "", null, null);
        when(jugadorRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(jugadorRepository.save(any(Jugador.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Jugador resultado = jugadorService.actualizar(1L, nuevosDatos);

        assertThat(resultado.getNombre()).isEqualTo("Vinicius Junior");
        assertThat(resultado.getMedia()).isEqualTo(91);
    }

    @Test
    void obtenerPorIdLanzaRuntimeExceptionSiNoExiste() {
        when(jugadorRepository.findById(55L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> jugadorService.obtenerPorId(55L));

        assertThat(exception.getMessage()).isEqualTo("Jugador no encontrado");
    }

    @Test
    void eliminarBorraJugador() {
        Jugador jugador = new Jugador(1L, "Erling Haaland", "DC", 24, "Noruega", 91, "", null, null);
        when(jugadorRepository.findById(1L)).thenReturn(Optional.of(jugador));
        when(cartaFUTRepository.findByJugadorId(1L)).thenReturn(Optional.empty());

        jugadorService.eliminar(1L);

        verify(jugadorRepository).delete(jugador);
    }
}
