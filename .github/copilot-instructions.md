# Copilot Instructions for AI Agents

## Project Overview
- **Monorepo** for a Spring Boot microservices course. Main module: `catalog-service` (see `pom.xml` for future/planned modules).
- Uses **Java 21**, Spring Boot 3.5, Maven, Docker Compose, and PostgreSQL.
- Key directories:
  - `catalog-service/`: Main microservice (REST API for product catalog)
  - `deployment/docker-compose/`: Docker Compose files for infra, apps, monitoring
  - `Taskfile.yml`: Defines all build, test, and infra workflows

## Architecture & Patterns
- **Domain-driven**: Business logic in `domain/`, REST controllers in `controllers/`, configuration in `config/`.
- **Persistence**: JPA repositories (e.g., `ProductRepository`) and Flyway migrations (`src/main/resources/db/migration/`).
- **DTO/Entity separation**: Use of mappers (see `ProductMapper`).
- **Custom properties**: See `ApplicationProperties.java` and `application.properties` for config like `catalog.page-size`.
- **Monitoring**: Actuator, Prometheus, and distributed tracing (see `application.properties` and Docker Compose monitoring stack).

## Developer Workflows
- **Build & Format**: `task format` (Spotless), `task test` (format + tests), `task build` (Docker images)
- **Run locally**: `task start_infra` (Postgres), then run Spring Boot app via IDE or `./mvnw spring-boot:run` in `catalog-service/`
- **Run all in Docker**: `task start` (infra + all apps)
- **Stop/Restart**: `task stop`, `task restart`, `task stop_infra`, etc.
- **Testing**: Uses JUnit, Testcontainers, and RestAssured. See `AbstractIT.java` for integration test setup.
- **API Docs**: OpenAPI/Swagger auto-generated (see `springdoc-openapi` dependency)

## Conventions & Tips
- **Service boundaries**: Each microservice is a Maven module. Only `catalog-service` is implemented; others are placeholders.
- **Configuration**: Prefer `application.properties` and `ApplicationProperties.java` for service-specific settings.
- **Database**: Use Flyway for schema/data changes. Place migrations in `db/migration/`.
- **Logging**: Use SLF4J (`log` in controllers/services). Logs are collected by Promtail in Docker.
- **Monitoring/Tracing**: Exposed via Actuator endpoints and Prometheus/Grafana stack in Docker Compose.
- **Docker images**: Built with Spring Boot's build-image goal, tagged as `dev2073/bookstore-<service>`.
- **Environment variables**: See `apps.yml` for required env vars when running in Docker.

## Key Files
- `Taskfile.yml`: All developer tasks (build, test, infra)
- `catalog-service/pom.xml`: Service dependencies and plugins
- `catalog-service/src/main/resources/application.properties`: Service config
- `deployment/docker-compose/`: Infra/app/monitoring orchestration
- `catalog-service/src/main/java/com/keelient/catalog_service/`: Main code (controllers, domain, config)

---

For new services, follow the `catalog-service` structure and update `Taskfile.yml` and Docker Compose files accordingly.
