package io.example.orders.infrastructure

import io.example.orders.domain.Order
import io.example.orders.domain.OrderId
import io.example.orders.domain.OrderStatus
import io.example.orders.infrastructure.entities.OrderEntity
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class OrderRepository(private val em: EntityManager) {
    fun save(order: Order) {
        val entity = OrderEntity(order.id.id, order.status.name, order.total, order.createdAt)
        em.merge(entity)
    }

    fun findById(id: UUID): Order? {
        val entity = em.find(OrderEntity::class.java, id) ?: return null
        return Order(OrderId(entity.id), OrderStatus.valueOf(entity.status), entity.createdAt, entity.total)
    }
}