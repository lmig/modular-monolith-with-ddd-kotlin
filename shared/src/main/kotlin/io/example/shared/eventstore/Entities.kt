package io.example.shared.eventstore

import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

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

    @Column(name = "created_at")
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

    @Column(name = "created_at")
    val createdAt: Instant = Instant.now()
)

@Entity
@Table(name = "inbox")
data class InboxEntity(
    @Id @GeneratedValue
    val id: Long = 0,

    @Column(name = "origin_aggregate_id")
    val originAggregateId: UUID? = null,

    val type: String,

    @Lob
    val payload: String,

    var processed: Boolean = false,

    @Column(name = "processed_at")
    var processedAt: Instant? = null,

    @Column(name = "created_at")
    val createdAt: Instant = Instant.now()
)