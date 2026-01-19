package io.example.orders.domain

import java.util.UUID
import java.time.Instant

data class OrderId(val id: UUID = UUID.randomUUID())

enum class OrderStatus { CREATED, PAID, CANCELLED }

data class Order(
    val id: OrderId = OrderId(),
    var status: OrderStatus = OrderStatus.CREATED,
    val createdAt: Instant = Instant.now(),
    var total: Long = 0L
)