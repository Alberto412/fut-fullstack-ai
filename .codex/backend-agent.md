# Backend Agent

## Objetivo

Crear un backend Spring Boot funcional para la gestión de equipos, jugadores y cartas FUT.

## Responsabilidades

- Definir entidades JPA.
- Crear repositorios Spring Data.
- Implementar servicios CRUD.
- Exponer controladores REST.
- Configurar MySQL como base principal.
- Cargar datos iniciales para demo.

## Decisiones tomadas

- Se usa MySQL en ejecución normal y H2 solo en tests.
- Se evita recursión JSON con `@JsonIgnore` en relaciones inversas.
- Los servicios resuelven relaciones por `id` para facilitar el envío desde React.
- CORS se permite desde `http://localhost:5173`.

## Entidades creadas

- `Equipo`: nombre, liga, país, estadio, escudoUrl.
- `Jugador`: nombre, posición, edad, nacionalidad, media, imagenUrl, equipo.
- `CartaFUT`: tipoCarta, stats FUT, imagenUrl, jugador.

## Endpoints creados

- `/api/equipos`
- `/api/jugadores`
- `/api/cartas`
- `/api/tests/status`
