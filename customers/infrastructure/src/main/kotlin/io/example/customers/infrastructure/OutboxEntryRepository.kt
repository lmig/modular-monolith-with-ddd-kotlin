package io.example.customers.infrastructure

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.UUID

@Repository
class OutboxEntryRepository(private val em: EntityManager) {
    private val mapper = jacksonObjectMapper()
    fun saveOutboxEntry(aggregateId: UUID, type: String, payload: Map<String, Any>) {
        val e = io.example.customers.infrastructure.entities.OutboxEntity(aggregateId = aggregateId, type = type, payload = mapper.writeValueAsString(payload))
        em.persist(e)
        em.flush()
    }
}