package com.futmanager.controller;

import com.futmanager.entity.CartaFUT;
import com.futmanager.service.CartaFUTService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:5173")
public class CartaFUTController {

    private final CartaFUTService cartaFUTService;

    public CartaFUTController(CartaFUTService cartaFUTService) {
        this.cartaFUTService = cartaFUTService;
    }

    @GetMapping
    public List<CartaFUT> listar() {
        return cartaFUTService.listar();
    }

    @GetMapping("/{id}")
    public CartaFUT obtenerPorId(@PathVariable Long id) {
        return cartaFUTService.obtenerPorId(id);
    }

    @PostMapping
    public CartaFUT crear(@RequestBody CartaFUT cartaFUT) {
        return cartaFUTService.crear(cartaFUT);
    }

    @PutMapping("/{id}")
    public CartaFUT actualizar(@PathVariable Long id, @RequestBody CartaFUT cartaFUT) {
        return cartaFUTService.actualizar(id, cartaFUT);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cartaFUTService.eliminar(id);
    }
}
