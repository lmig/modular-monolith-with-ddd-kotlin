package io.example.orders.infrastructure

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.UUID

@Repository
class OutboxEntryRepository(private val em: EntityManager) {
    private val mapper = jacksonObjectMapper()

    fun saveOutboxEntry(aggregateId: UUID, type: String, payload: Map<String, Any>, destination: String? = null) {
        val e = OutboxEntity(aggregateId = aggregateId, type = type, payload = mapper.writeValueAsString(payload), destination = destination)
        em.persist(e)
        em.flush()
    }
}