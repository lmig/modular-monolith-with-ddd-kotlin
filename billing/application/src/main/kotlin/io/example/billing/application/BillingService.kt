package io.example.billing.application

import io.example.billing.domain.Invoice
import io.example.billing.infrastructure.InvoiceRepository
import io.example.shared.eventstore.SharedEventStoreRepository
import io.example.shared.eventstore.SharedOutboxRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class BillingService(
    private val repo: InvoiceRepository,
    private val eventStore: SharedEventStoreRepository,
    private val outbox: SharedOutboxRepository
) {
    @Transactional
    fun createInvoice(customerId: UUID, amount: Long): Invoice {
        val invoice = Invoice(customerId = customerId, amount = amount)
        repo.save(invoice)
        eventStore.appendEvent(invoice.id.id, "InvoiceCreated", mapOf("customerId" to customerId.toString(), "amount" to amount))
        outbox.saveOutboxEntry(invoice.id.id, "InvoiceCreated", mapOf("customerId" to customerId.toString(), "amount" to amount))
        return invoice
    }

    @Transactional
    fun markPaid(id: UUID) {
        val invoice = repo.findById(id) ?: throw RuntimeException("Invoice not found")
        invoice.paid = true
        repo.save(invoice)
        eventStore.appendEvent(invoice.id.id, "InvoicePaid", emptyMap())
        outbox.saveOutboxEntry(invoice.id.id, "InvoicePaid", emptyMap())
    }
}