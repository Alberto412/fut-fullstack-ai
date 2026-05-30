package com.futmanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.futmanager.entity.Equipo;
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
class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @Mock
    private JugadorRepository jugadorRepository;

    @InjectMocks
    private EquipoService equipoService;

    @Test
    void listarDevuelveEquipos() {
        when(equipoRepository.findAll()).thenReturn(List.of(new Equipo(1L, "Real Madrid", "LaLiga", "España", "Bernabéu", "", List.of())));

        List<Equipo> equipos = equipoService.listar();

        assertThat(equipos).hasSize(1);
        assertThat(equipos.get(0).getNombre()).isEqualTo("Real Madrid");
    }

    @Test
    void crearGuardaEquipoSinId() {
        Equipo entrada = new Equipo(99L, "FC Barcelona", "LaLiga", "España", "Camp Nou", "", List.of());
        Equipo guardado = new Equipo(1L, "FC Barcelona", "LaLiga", "España", "Camp Nou", "", List.of());
        when(equipoRepository.save(any(Equipo.class))).thenReturn(guardado);

        Equipo resultado = equipoService.crear(entrada);

        assertThat(resultado.getId()).isEqualTo(1L);
        verify(equipoRepository).save(any(Equipo.class));
    }

    @Test
    void actualizarModificaDatos() {
        Equipo existente = new Equipo(1L, "Antiguo", "Liga", "Pais", "Estadio", "", List.of());
        Equipo nuevosDatos = new Equipo(null, "Manchester City", "Premier League", "Inglaterra", "Etihad Stadium", "", List.of());
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(equipoRepository.save(any(Equipo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Equipo resultado = equipoService.actualizar(1L, nuevosDatos);

        assertThat(resultado.getNombre()).isEqualTo("Manchester City");
        assertThat(resultado.getEstadio()).isEqualTo("Etihad Stadium");
    }

    @Test
    void obtenerPorIdLanzaRuntimeExceptionSiNoExiste() {
        when(equipoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> equipoService.obtenerPorId(99L));

        assertThat(exception.getMessage()).isEqualTo("Equipo no encontrado");
    }

    @Test
    void eliminarDesvinculaJugadoresYBorraEquipo() {
        Equipo equipo = new Equipo(1L, "Real Madrid", "LaLiga", "España", "Bernabéu", "", List.of());
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));
        when(jugadorRepository.findByEquipoId(1L)).thenReturn(List.of());

        equipoService.eliminar(1L);

        verify(jugadorRepository).saveAll(List.of());
        verify(equipoRepository).delete(equipo);
    }
}
