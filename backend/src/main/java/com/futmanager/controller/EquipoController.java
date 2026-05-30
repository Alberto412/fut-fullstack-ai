package com.futmanager.controller;

import com.futmanager.dto.EquipoDTO;
import com.futmanager.entity.Equipo;
import com.futmanager.mapper.EquipoMapper;
import com.futmanager.service.EquipoService;
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
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;
    private final EquipoMapper equipoMapper;

    public EquipoController(EquipoService equipoService, EquipoMapper equipoMapper) {
        this.equipoService = equipoService;
        this.equipoMapper = equipoMapper;
    }

    @GetMapping
    public List<EquipoDTO> listar() {
        return equipoMapper.toDTOList(equipoService.listar());
    }

    @GetMapping("/{id}")
    public EquipoDTO obtenerPorId(@PathVariable Long id) {
        return equipoMapper.toDTO(equipoService.obtenerPorId(id));
    }

    @PostMapping
    public EquipoDTO crear(@RequestBody EquipoDTO equipoDTO) {
        Equipo equipo = equipoMapper.toEntity(equipoDTO);
        return equipoMapper.toDTO(equipoService.crear(equipo));
    }

    @PutMapping("/{id}")
    public EquipoDTO actualizar(@PathVariable Long id, @RequestBody EquipoDTO equipoDTO) {
        Equipo equipo = equipoMapper.toEntity(equipoDTO);
        return equipoMapper.toDTO(equipoService.actualizar(id, equipo));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        equipoService.eliminar(id);
    }
}
