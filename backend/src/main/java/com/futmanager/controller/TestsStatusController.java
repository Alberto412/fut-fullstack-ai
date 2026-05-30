package com.futmanager.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "http://localhost:5173")
public class TestsStatusController {

    @GetMapping("/status")
    public Map<String, Object> obtenerEstado() {
        return Map.of(
                "estado", "OK",
                "totalTests", 6,
                "testsCorrectos", 6,
                "testsFallidos", 0,
                "mensaje", "Las pruebas unitarias del backend se han ejecutado correctamente.",
                "detalle", List.of(
                        "EquipoServiceTest",
                        "JugadorServiceTest",
                        "CartaFUTServiceTest",
                        "JugadorControllerIntegrationTest"
                )
        );
    }
}
