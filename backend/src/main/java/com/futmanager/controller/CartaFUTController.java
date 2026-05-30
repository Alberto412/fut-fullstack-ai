package com.futmanager.controller;

import com.futmanager.dto.CartaFUTDTO;
import com.futmanager.entity.CartaFUT;
import com.futmanager.mapper.CartaFUTMapper;
import com.futmanager.service.CartaFUTService;
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
@RequestMapping("/api/cartas")
public class CartaFUTController {

    private final CartaFUTService cartaFUTService;
    private final CartaFUTMapper cartaFUTMapper;

    public CartaFUTController(CartaFUTService cartaFUTService, CartaFUTMapper cartaFUTMapper) {
        this.cartaFUTService = cartaFUTService;
        this.cartaFUTMapper = cartaFUTMapper;
    }

    @GetMapping
    public List<CartaFUTDTO> listar() {
        return cartaFUTMapper.toDTOList(cartaFUTService.listar());
    }

    @GetMapping("/{id}")
    public CartaFUTDTO obtenerPorId(@PathVariable Long id) {
        return cartaFUTMapper.toDTO(cartaFUTService.obtenerPorId(id));
    }

    @PostMapping
    public CartaFUTDTO crear(@RequestBody CartaFUTDTO cartaFUTDTO) {
        CartaFUT cartaFUT = cartaFUTMapper.toEntity(cartaFUTDTO);
        return cartaFUTMapper.toDTO(cartaFUTService.crear(cartaFUT));
    }

    @PutMapping("/{id}")
    public CartaFUTDTO actualizar(@PathVariable Long id, @RequestBody CartaFUTDTO cartaFUTDTO) {
        CartaFUT cartaFUT = cartaFUTMapper.toEntity(cartaFUTDTO);
        return cartaFUTMapper.toDTO(cartaFUTService.actualizar(id, cartaFUT));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cartaFUTService.eliminar(id);
    }
}
