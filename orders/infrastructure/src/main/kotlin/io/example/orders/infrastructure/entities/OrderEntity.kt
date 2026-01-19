package io.example.orders.infrastructure.entities

import jakarta.persistence.*
import java.util.UUID
import java.time.Instant

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    val id: UUID,
    var status: String,
    var total: Long,
    val createdAt: Instant
)