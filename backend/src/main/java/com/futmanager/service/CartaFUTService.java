package com.futmanager.service;

import com.futmanager.entity.CartaFUT;
import com.futmanager.entity.Jugador;
import com.futmanager.repository.CartaFUTRepository;
import com.futmanager.repository.JugadorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartaFUTService {

    private final CartaFUTRepository cartaFUTRepository;
    private final JugadorRepository jugadorRepository;

    public CartaFUTService(CartaFUTRepository cartaFUTRepository, JugadorRepository jugadorRepository) {
        this.cartaFUTRepository = cartaFUTRepository;
        this.jugadorRepository = jugadorRepository;
    }

    public List<CartaFUT> listar() {
        return cartaFUTRepository.findAll();
    }

    public CartaFUT obtenerPorId(Long id) {
        return cartaFUTRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carta FUT no encontrada"));
    }

    public CartaFUT crear(CartaFUT cartaFUT) {
        cartaFUT.setId(null);
        cartaFUT.setJugador(resolverJugador(cartaFUT.getJugador()));
        return cartaFUTRepository.save(cartaFUT);
    }

    public CartaFUT actualizar(Long id, CartaFUT datos) {
        CartaFUT cartaFUT = obtenerPorId(id);
        cartaFUT.setTipoCarta(datos.getTipoCarta());
        cartaFUT.setRitmo(datos.getRitmo());
        cartaFUT.setTiro(datos.getTiro());
        cartaFUT.setPase(datos.getPase());
        cartaFUT.setRegate(datos.getRegate());
        cartaFUT.setDefensa(datos.getDefensa());
        cartaFUT.setFisico(datos.getFisico());
        cartaFUT.setImagenUrl(datos.getImagenUrl());
        cartaFUT.setJugador(resolverJugador(datos.getJugador()));
        return cartaFUTRepository.save(cartaFUT);
    }

    public void eliminar(Long id) {
        CartaFUT cartaFUT = obtenerPorId(id);
        cartaFUTRepository.delete(cartaFUT);
    }

    private Jugador resolverJugador(Jugador jugador) {
        if (jugador == null || jugador.getId() == null) {
            return null;
        }
        return jugadorRepository.findById(jugador.getId())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }
}
