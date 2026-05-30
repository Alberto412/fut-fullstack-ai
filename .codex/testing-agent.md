# Testing Agent

## Tests implementados

- `EquipoServiceTest`
- `JugadorServiceTest`
- `CartaFUTServiceTest`
- `JugadorControllerIntegrationTest`

## Herramientas

- JUnit 5.
- Mockito.
- AssertJ.
- MockMvc.
- H2 en memoria con perfil `test`.

## Cómo ejecutar

```bash
cd backend
mvn test
```

## Qué valida cada test

- `EquipoServiceTest`: listar, crear, actualizar, eliminar y caso no encontrado.
- `JugadorServiceTest`: listar, crear con equipo, actualizar, eliminar y caso no encontrado.
- `CartaFUTServiceTest`: listar, crear con jugador, actualizar, eliminar y caso no encontrado.
- `JugadorControllerIntegrationTest`: comprueba que `GET /api/jugadores` responde `200 OK`.
