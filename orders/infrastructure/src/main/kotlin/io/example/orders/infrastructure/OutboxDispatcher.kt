package io.example.orders.infrastructure

import jakarta.persistence.EntityManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Component
class OutboxDispatcher(private val em: EntityManager) {

    // Polls the outbox, creates inbox entries (DB-based broker) and marks outbox dispatched.
    @Scheduled(fixedDelayString = "3000")
    @Transactional
    fun dispatch() {
        val q = em.createQuery("SELECT o FROM OutboxEntity o WHERE o.dispatched = false", OutboxEntity::class.java)
            .resultList
        for (entry in q) {
            val inbox = InboxEntity(
                originAggregateId = entry.aggregateId,
                type = entry.type,
                payload = entry.payload
            )
            em.persist(inbox)
            entry.dispatched = true
            em.merge(entry)
        }
        em.flush()
    }
}