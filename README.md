# FUT Fullstack AI

Aplicación full stack para la actividad **App Full Stack con IA**. Incluye backend Spring Boot, frontend React, base de datos MySQL, CRUD completo de `Equipo`, `Jugador` y `CartaFUT`, endpoint de estado de tests y documentación de agentes IA.

## Tecnologías

- Backend: Java 17, Spring Boot 3, Spring Web, Spring Data JPA, Maven.
- Frontend: React 18, Vite, CSS propio, lucide-react.
- Base de datos: MySQL 8.0 con phpMyAdmin.
- Tests: JUnit 5, Mockito, MockMvc, H2 para perfil de pruebas.

## Estructura

```text
fut-fullstack-ai/
├── backend/
│   ├── pom.xml
│   ├── README.md
│   └── src/
├── frontend/
│   ├── package.json
│   ├── vite.config.js
│   └── src/
├── .codex/
│   ├── Agents.md
│   ├── backend-agent.md
│   ├── frontend-agent.md
│   ├── testing-agent.md
│   ├── review-agent.md
│   └── dto-mapping-agent.md
├── docker-compose.yml
└── README.md
```

## Requisitos

- Java 17.
- Maven.
- Node.js 18 o superior.
- Docker y Docker Compose.

## Docker y MySQL

```bash
docker compose up -d
```

Si tu instalación usa Compose v1:

```bash
docker-compose up -d
```

- MySQL: `localhost:3355`
- Base de datos: `pokedex_db`
- Usuario: `root`
- Password: `root`
- phpMyAdmin: `http://localhost:8095`

## Backend

```bash
cd backend
mvn spring-boot:run
```

URL: `http://localhost:8081`

La configuración principal usa:

```properties
spring.datasource.url=jdbc:mysql://localhost:3355/pokedex_db?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=create
```

## Frontend

```bash
cd frontend
npm install
npm run dev
```

URL: `http://localhost:5173`

## Endpoints

- `GET /api/equipos`
- `GET /api/equipos/{id}`
- `POST /api/equipos`
- `PUT /api/equipos/{id}`
- `DELETE /api/equipos/{id}`
- `GET /api/jugadores`
- `GET /api/jugadores/{id}`
- `POST /api/jugadores`
- `PUT /api/jugadores/{id}`
- `DELETE /api/jugadores/{id}`
- `GET /api/cartas`
- `GET /api/cartas/{id}`
- `POST /api/cartas`
- `PUT /api/cartas/{id}`
- `DELETE /api/cartas/{id}`
- `GET /api/tests/status`

## Tests

```bash
cd backend
mvn test
```

Existe un endpoint de resumen para el frontend:

```http
GET http://localhost:8081/api/tests/status
```

Este endpoint no ejecuta Maven. Solo expone un resumen estático del estado esperado de pruebas para que React pueda mostrarlo en la pantalla de tests.

## Carpeta `.codex`

La carpeta `.codex` documenta un flujo de agentes IA usado para organizar el proyecto:

- `Agents.md`: visión general del flujo de agentes.
- `backend-agent.md`: decisiones de backend.
- `frontend-agent.md`: decisiones de frontend.
- `testing-agent.md`: pruebas creadas.
- `review-agent.md`: candidato de mejora para ejercicio 2.
- `dto-mapping-agent.md`: documentación de DTOs y mapeo automático con MapStruct.

## DTOs y mapeo automático

Los DTOs son objetos de transporte que separan la API REST de las entidades JPA. En este proyecto se usan para exponer datos claros al frontend sin depender directamente de las relaciones internas de Hibernate.

Se han añadido DTOs principales para `Equipo`, `Jugador` y `CartaFUT`, además de DTOs resumen para relaciones: `EquipoResumenDTO` y `JugadorResumenDTO`. Así, un jugador devuelve un equipo resumido y una carta devuelve un jugador resumido, evitando recursión y JSON excesivo.

El mapeo automático se realiza con MapStruct mediante:

- `EquipoMapper`
- `JugadorMapper`
- `CartaFUTMapper`

Los endpoints siguen disponibles con las mismas rutas. El frontend puede seguir enviando relaciones como `{ "equipo": { "id": 1 } }` y `{ "jugador": { "id": 1 } }`.

## Guion básico de demo

1. Levantar MySQL con `docker compose up -d`.
2. Arrancar backend con `mvn spring-boot:run`.
3. Abrir `http://localhost:8081/api/jugadores` y enseñar datos iniciales.
4. Arrancar frontend con `npm run dev`.
5. Abrir `http://localhost:5173` y enseñar dashboard.
6. Crear un jugador desde React seleccionando equipo.
7. Editar y eliminar un registro.
8. Abrir la pantalla de tests y enseñar `/api/tests/status`.
9. Entrar en phpMyAdmin y enseñar `pokedex_db`.

## Flujo recomendado para ejercicio 3

Crear jugador desde React:

```text
React formulario Jugadores
→ POST http://localhost:8081/api/jugadores
→ JugadorController
→ JugadorService
→ JugadorRepository
→ MySQL tabla jugador
→ respuesta JSON
→ React actualiza listado
```

## Ejercicio 4

El backend ya incorpora DTOs y MapStruct. Los controladores reciben y devuelven DTOs, los servicios mantienen la lógica interna con entidades y las relaciones se resuelven por ID antes de guardar.
