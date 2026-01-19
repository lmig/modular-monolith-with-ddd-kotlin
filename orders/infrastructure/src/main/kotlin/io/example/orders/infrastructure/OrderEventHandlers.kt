package io.example.orders.infrastructure

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import io.example.shared.eventstore.InboxEvent

// Handler local que consume los eventos publicados por SharedInboxProcessor (Shared InboxEvent)
@Component
class OrderEventHandlers {
    private val log = LoggerFactory.getLogger(javaClass)
    private val mapper = jacksonObjectMapper()

    @EventListener
    fun handleInboxEvent(event: InboxEvent) {
        when (event.type) {
            "OrderCreated" -> {
                val payload = mapper.readTree(event.payload)
                log.info("Handled OrderCreated via shared inbox: total=${payload["total"].asLong()}")
                // Proyección / lógica aquí...
            }
            "OrderPaid" -> {
                log.info("Handled OrderPaid via shared inbox")
            }
            else -> {
                log.debug("Unhandled event type: ${event.type}")
            }
        }
    }
}