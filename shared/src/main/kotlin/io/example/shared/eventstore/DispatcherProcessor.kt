package io.example.shared.eventstore

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import jakarta.persistence.EntityManager

@Component
class SharedOutboxDispatcher(private val em: EntityManager) {
    private val log = LoggerFactory.getLogger(javaClass)

    // Poll the outbox and create inbox entries (DB-based broker)
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
        if (q.isNotEmpty()) log.debug("Dispatched ${q.size} outbox entries -> created inbox entries")
    }
}

@Component
class SharedInboxProcessor(private val em: EntityManager, private val publisher: ApplicationEventPublisher) {
    private val log = LoggerFactory.getLogger(javaClass)

    // publish local Spring events so local handlers can consume
    @Scheduled(fixedDelayString = "3000")
    @Transactional
    fun process() {
        val q = em.createQuery("SELECT i FROM InboxEntity i WHERE i.processed = false", InboxEntity::class.java)
            .resultList
        for (entry in q) {
            try {
                publisher.publishEvent(InboxEvent(entry.type, entry.payload))
                entry.processed = true
                entry.processedAt = java.time.Instant.now()
                em.merge(entry)
            } catch (ex: Exception) {
                log.error("Failed processing inbox id=${entry.id}", ex)
            }
        }
        em.flush()
        if (q.isNotEmpty()) log.debug("Processed ${q.size} inbox entries")
    }
}

data class InboxEvent(val type: String, val payload: String)