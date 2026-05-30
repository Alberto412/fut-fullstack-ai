# Frontend Agent

## Objetivo

Crear una interfaz React presentable para operar todo el CRUD de la aplicación.

## Responsabilidades

- Dashboard inicial estilo FUT.
- CRUD completo de equipos.
- CRUD completo de jugadores con selector de equipo.
- CRUD completo de cartas FUT con selector de jugador.
- Pantalla de estado de tests.
- Manejo de carga, errores y confirmación al eliminar.

## Páginas creadas

- `Home.jsx`
- `Equipos.jsx`
- `Jugadores.jsx`
- `Cartas.jsx`
- `TestsStatus.jsx`

## Flujo de comunicación con backend

El frontend usa `fetch` desde `src/api/api.js` contra `http://localhost:8090`. Tras crear, editar o eliminar, cada página recarga sus datos para mantener el listado sincronizado.
