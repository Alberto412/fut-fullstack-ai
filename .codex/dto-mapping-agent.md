# DTO Mapping Agent

## Objetivo

Documentar la ampliación del ejercicio 4: DTOs y mapeo automático con MapStruct.

## Qué son DTOs

Los DTOs son objetos diseñados para transportar datos entre capas o entre backend y frontend. Ayudan a no exponer directamente las entidades JPA y permiten controlar qué campos viajan en cada petición o respuesta.

## DTOs creados

- `EquipoDTO`: datos completos de un equipo para CRUD.
- `EquipoResumenDTO`: datos mínimos de equipo dentro de un jugador.
- `JugadorDTO`: datos completos de un jugador con equipo resumido.
- `JugadorResumenDTO`: datos mínimos de jugador dentro de una carta FUT.
- `CartaFUTDTO`: datos completos de una carta con jugador resumido.

## Qué es MapStruct

MapStruct es una librería que genera código de mapeo en tiempo de compilación. Evita escribir conversiones repetitivas a mano y deja el flujo Entity/DTO más claro.

## Mappers creados

- `EquipoMapper`: convierte `Equipo`, `EquipoDTO` y `EquipoResumenDTO`.
- `JugadorMapper`: convierte `Jugador`, `JugadorDTO` y `JugadorResumenDTO`.
- `CartaFUTMapper`: convierte `CartaFUT` y `CartaFUTDTO`.

## Flujo de datos

```text
React JSON
-> Controller recibe DTO
-> Mapper convierte DTO a Entity
-> Service resuelve relaciones por ID y guarda
-> Repository persiste en MySQL
-> Mapper convierte Entity a DTO
-> Controller devuelve JSON al frontend
```

## Nota de compatibilidad

El frontend mantiene el formato de relaciones anidadas por ID, por ejemplo `{ "equipo": { "id": 1 } }` y `{ "jugador": { "id": 1 } }`.
