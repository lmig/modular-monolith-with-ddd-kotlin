package io.example.orders.application

import io.example.orders.domain.Order
import io.example.orders.domain.OrderId
import io.example.orders.infrastructure.OrderRepository
import io.example.shared.eventstore.SharedEventStoreRepository
import io.example.shared.eventstore.SharedOutboxRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val eventStore: SharedEventStoreRepository,
    private val outbox: SharedOutboxRepository
) {
    @Transactional
    fun createOrder(total: Long): Order {
        val order = Order(total = total)
        orderRepository.save(order)
        // append event to shared event store
        eventStore.appendEvent(order.id.id, "OrderCreated", mapOf("total" to total))
        // write outbox entry via shared outbox
        outbox.saveOutboxEntry(order.id.id, "OrderCreated", mapOf("total" to total))
        return order
    }

    @Transactional
    fun markPaid(id: UUID) {
        val order = orderRepository.findById(id) ?: throw RuntimeException("Order not found")
        order.status = io.example.orders.domain.OrderStatus.PAID
        orderRepository.save(order)
        eventStore.appendEvent(order.id.id, "OrderPaid", emptyMap())
        outbox.saveOutboxEntry(order.id.id, "OrderPaid", emptyMap())
    }
}