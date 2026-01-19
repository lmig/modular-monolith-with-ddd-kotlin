package io.example.orders.infrastructure

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
    val destination: String? = null,
    var dispatched: Boolean = false,
    val createdAt: Instant = Instant.now()
)

@Entity
@Table(name = "inbox")
data class InboxEntity(
    @Id @GeneratedValue
    val id: Long = 0,
    @Column(name = "origin_aggregate_id")
    val originAggregateId: UUID?,
    val type: String,
    @Lob
    val payload: String,
    var processed: Boolean = false,
    var processedAt: Instant? = null,
    val createdAt: Instant = Instant.now()
)