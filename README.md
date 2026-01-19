```markdown
# modular-monolith-with-ddd-kotlin

Port del proyecto kgrzybek/modular-monolith-with-ddd a Kotlin + Spring Boot.
- Multi-módulo Gradle (Kotlin DSL)
- Bounded context de referencia: orders
- Postgres para datos, esqueleto de Event Store + Outbox (DB-based messaging)
- Flyway para migraciones
- Gradual port: se empezará por `orders` y se extenderá a otros contextos.

Estructura:
- settings.gradle.kts
- build.gradle.kts (root)
- modules:
  - orders/domain
  - orders/application
  - orders/infrastructure
  - orders/api

Para arrancar localmente:
1. Instala Java 17+ y Gradle (o usa wrapper).
2. Configura Postgres (puedes usar docker-compose).
3. Ejecuta `./gradlew :orders:api:bootRun`

Push al repositorio (si vas a subir tú mismo):
- Crea el repo en GitHub: `lmig/modular-monolith-with-ddd-kotlin`.
- Añade los archivos y ejecuta los comandos de Git abajo en "Instrucciones para subir".

Licencia: MIT (misma que el original).
```