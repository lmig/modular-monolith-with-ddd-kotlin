package io.example.billing.domain

import java.util.UUID
import java.time.Instant

data class InvoiceId(val id: UUID = UUID.randomUUID())

data class Invoice(
    val id: InvoiceId = InvoiceId(),
    val customerId: UUID,
    val amount: Long,
    var paid: Boolean = false,
    val createdAt: Instant = Instant.now()
)