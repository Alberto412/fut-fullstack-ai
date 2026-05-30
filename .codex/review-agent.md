# Review Agent

## Objetivo

Preparar una revisión técnica para el ejercicio 2.

## Qué revisar

Los servicios usan `RuntimeException` genérica cuando no encuentran un registro por ID:

- `Equipo no encontrado`
- `Jugador no encontrado`
- `Carta FUT no encontrada`

## Pista de mejora

Esta implementación funciona para el CRUD, pero es una candidata clara para mejorar. En una iteración posterior se podría usar `ResponseStatusException(HttpStatus.NOT_FOUND)` o una excepción personalizada con un manejador global.

## Estado

No se ha corregido todavía porque se deja como mejora intencionada para documentar en el ejercicio 2.
