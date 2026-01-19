package io.example.customers.infrastructure

import jakarta.persistence.*
import java.util.UUID
import java.time.Instant

@Entity
@Table(name = "event_store")
data class EventEntity(
    @Id @GeneratedValue
    val id: Long = 0,
    @Column(name = "aggregate_id")
    val aggregateId: UUID,
    val type: String,
    @Lob
    val payload: String,
    val createdAt: Instant = Instant.now()
)

@Entity
@Table(name = "outbox")
data class OutboxEntity(
    @Id @GeneratedValue
    val id: Long = 0,
    @Column(name = "aggregate_id")
    val aggregateId: UUID,
    val type: String,
    @Lob
    val payload: String,
    var dispatched: Boolean = false,
    val createdAt: Instant = Instant.now()
)