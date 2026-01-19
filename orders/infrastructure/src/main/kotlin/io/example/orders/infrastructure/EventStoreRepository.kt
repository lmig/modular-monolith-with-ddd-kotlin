package io.example.orders.infrastructure

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.UUID

@Repository
class EventStoreRepository(private val em: EntityManager) {
    private val mapper = jacksonObjectMapper()

    fun appendEvent(aggregateId: UUID, type: String, payload: Map<String, Any>) {
        val entity = EventEntity(aggregateId = aggregateId, type = type, payload = mapper.writeValueAsString(payload))
        em.persist(entity)
        em.flush()
    }
}