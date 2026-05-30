package com.futmanager.config;

import com.futmanager.entity.CartaFUT;
import com.futmanager.entity.Equipo;
import com.futmanager.entity.Jugador;
import com.futmanager.repository.CartaFUTRepository;
import com.futmanager.repository.EquipoRepository;
import com.futmanager.repository.JugadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatosIniciales(
            EquipoRepository equipoRepository,
            JugadorRepository jugadorRepository,
            CartaFUTRepository cartaFUTRepository
    ) {
        return args -> {
            if (equipoRepository.count() > 0) {
                return;
            }

            Equipo realMadrid = equipoRepository.save(new Equipo(null, "Real Madrid", "LaLiga", "España", "Santiago Bernabéu", "", null));
            Equipo manchesterCity = equipoRepository.save(new Equipo(null, "Manchester City", "Premier League", "Inglaterra", "Etihad Stadium", "", null));
            Equipo barcelona = equipoRepository.save(new Equipo(null, "FC Barcelona", "LaLiga", "España", "Camp Nou", "", null));

            Jugador vinicius = jugadorRepository.save(new Jugador(null, "Vinicius Junior", "EI", 24, "Brasil", 90, "", realMadrid, null));
            Jugador bellingham = jugadorRepository.save(new Jugador(null, "Jude Bellingham", "MC", 21, "Inglaterra", 91, "", realMadrid, null));
            Jugador haaland = jugadorRepository.save(new Jugador(null, "Erling Haaland", "DC", 24, "Noruega", 91, "", manchesterCity, null));
            Jugador yamal = jugadorRepository.save(new Jugador(null, "Lamine Yamal", "ED", 17, "España", 84, "", barcelona, null));

            cartaFUTRepository.save(new CartaFUT(null, "Oro raro", 95, 84, 81, 91, 35, 75, "", vinicius));
            cartaFUTRepository.save(new CartaFUT(null, "TOTY", 85, 88, 90, 92, 83, 88, "", bellingham));
            cartaFUTRepository.save(new CartaFUT(null, "Oro raro", 89, 93, 70, 80, 45, 88, "", haaland));
            cartaFUTRepository.save(new CartaFUT(null, "Promesa", 88, 78, 80, 86, 30, 60, "", yamal));
        };
    }
}
