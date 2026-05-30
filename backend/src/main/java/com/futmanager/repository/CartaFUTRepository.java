package com.futmanager.repository;

import com.futmanager.entity.CartaFUT;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaFUTRepository extends JpaRepository<CartaFUT, Long> {

    Optional<CartaFUT> findByJugadorId(Long jugadorId);
}
