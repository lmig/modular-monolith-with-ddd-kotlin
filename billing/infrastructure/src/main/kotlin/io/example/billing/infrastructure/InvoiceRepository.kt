package io.example.billing.infrastructure

import jakarta.persistence.EntityManager
import io.example.billing.domain.Invoice
import io.example.billing.domain.InvoiceId
import io.example.billing.infrastructure.entities.InvoiceEntity
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class InvoiceRepository(private val em: EntityManager) {
    fun save(invoice: Invoice) {
        val e = InvoiceEntity(invoice.id.id, invoice.customerId, invoice.amount, invoice.paid, invoice.createdAt)
        em.merge(e)
    }

    fun findById(id: UUID): Invoice? {
        val e = em.find(InvoiceEntity::class.java, id) ?: return null
        return Invoice(InvoiceId(e.id), e.customerId, e.amount, e.paid, e.createdAt)
    }
}