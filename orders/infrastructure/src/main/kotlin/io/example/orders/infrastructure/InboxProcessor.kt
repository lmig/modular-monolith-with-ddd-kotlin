package io.example.orders.infrastructure

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.EntityManager
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Component
class InboxProcessor(
    private val em: EntityManager,
    private val publisher: ApplicationEventPublisher
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val mapper = jacksonObjectMapper()

    // Poll inbox entries and publish Spring events for local handlers.
    @Scheduled(fixedDelayString = "3000")
    @Transactional
    fun process() {
        val q = em.createQuery("SELECT i FROM InboxEntity i WHERE i.processed = false", InboxEntity::class.java)
            .resultList
        for (entry in q) {
            try {
                // Publish a Spring event containing the raw payload and type
                publisher.publishEvent(InboxEvent(entry.type, entry.payload))
                entry.processed = true
                entry.processedAt = Instant.now()
                em.merge(entry)
            } catch (ex: Exception) {
                log.error("Failed processing inbox ${entry.id}", ex)
                // keep unprocessed for retry
            }
        }
        em.flush()
    }
}

data class InboxEvent(val type: String, val payload: String)