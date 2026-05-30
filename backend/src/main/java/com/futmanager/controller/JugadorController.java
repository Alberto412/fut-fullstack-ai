package com.futmanager.controller;

import com.futmanager.dto.JugadorDTO;
import com.futmanager.entity.Jugador;
import com.futmanager.mapper.JugadorMapper;
import com.futmanager.service.JugadorService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;
    private final JugadorMapper jugadorMapper;

    public JugadorController(JugadorService jugadorService, JugadorMapper jugadorMapper) {
        this.jugadorService = jugadorService;
        this.jugadorMapper = jugadorMapper;
    }

    @GetMapping
    public List<JugadorDTO> listar() {
        return jugadorMapper.toDTOList(jugadorService.listar());
    }

    @GetMapping("/{id}")
    public JugadorDTO obtenerPorId(@PathVariable Long id) {
        return jugadorMapper.toDTO(jugadorService.obtenerPorId(id));
    }

    @PostMapping
    public JugadorDTO crear(@RequestBody JugadorDTO jugadorDTO) {
        Jugador jugador = jugadorMapper.toEntity(jugadorDTO);
        return jugadorMapper.toDTO(jugadorService.crear(jugador));
    }

    @PutMapping("/{id}")
    public JugadorDTO actualizar(@PathVariable Long id, @RequestBody JugadorDTO jugadorDTO) {
        Jugador jugador = jugadorMapper.toEntity(jugadorDTO);
        return jugadorMapper.toDTO(jugadorService.actualizar(id, jugador));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        jugadorService.eliminar(id);
    }
}
