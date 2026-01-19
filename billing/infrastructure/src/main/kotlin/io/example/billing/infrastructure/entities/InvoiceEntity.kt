package io.example.billing.infrastructure.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID
import java.time.Instant

@Entity
@Table(name = "invoices")
data class InvoiceEntity(
    @Id
    val id: UUID,
    val customerId: UUID,
    var amount: Long,
    var paid: Boolean,
    val createdAt: Instant
)