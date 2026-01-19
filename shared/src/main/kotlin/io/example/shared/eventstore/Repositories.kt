package io.example.shared.eventstore

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class SharedEventStoreRepository(private val em: EntityManager) {
    private val mapper = jacksonObjectMapper()

    fun appendEvent(aggregateId: UUID, type: String, payload: Map<String, Any>) {
        val entity = EventEntity(aggregateId = aggregateId, type = type, payload = mapper.writeValueAsString(payload))
        em.persist(entity)
        em.flush()
    }
}

@Repository
class SharedOutboxRepository(private val em: EntityManager) {
    private val mapper = jacksonObjectMapper()

    fun saveOutboxEntry(aggregateId: UUID, type: String, payload: Map<String, Any>, destination: String? = null) {
        val e = OutboxEntity(aggregateId = aggregateId, type = type, payload = mapper.writeValueAsString(payload), destination = destination)
        em.persist(e)
        em.flush()
    }
}

@Repository
class SharedInboxRepository(private val em: EntityManager) {
    fun findUnprocessed(): List<InboxEntity> {
        val q = em.createQuery("SELECT i FROM InboxEntity i WHERE i.processed = false", InboxEntity::class.java)
        return q.resultList
    }

    fun persistInbox(inbox: InboxEntity) {
        em.persist(inbox)
        em.flush()
    }

    fun markProcessed(inbox: InboxEntity) {
        inbox.processed = true
        inbox.processedAt = java.time.Instant.now()
        em.merge(inbox)
        em.flush()
    }
}