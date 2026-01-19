1) Seguridad JWT:
   - Accede a POST /auth/login con body {"username":"admin","password":"admin"} para obtener token.
   - Incluye encabezado Authorization: Bearer <token> en llamadas a /api/**.

2) Messaging (DB-only):
   - Cuando un bounded context escribe en outbox, el OutboxDispatcher (poller) crea entradas en inbox.
   - InboxProcessor (poller) publica Spring events que los handlers locales consumen.
   - Esto emula un broker únicamente con la base de datos (Outbox → Inbox).

3) MapStruct / mappers:
   - Se incluye la interfaz MapStruct OrderMapper y una implementación Kotlin manual OrderMapperImpl.
   - Si quieres usar MapStruct generado por kapt, habilita kapt plugin y ejecuta ./gradlew build.

4) Customers:
   - Módulo skeleton creado. Continúo portando su domain/application/infrastructure/api al siguiente push si lo confirmas.

5) Siguientes pasos recomendados:
   - Reemplazar user service en memoria por persistencia JPA.
   - Implementar proyecciones (read models) por cada contexto.
   - Implementar tests end-to-end usando Testcontainers.