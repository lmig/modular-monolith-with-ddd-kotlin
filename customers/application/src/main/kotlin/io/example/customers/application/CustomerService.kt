package io.example.customers.application

import io.example.customers.domain.Customer
import io.example.customers.infrastructure.CustomerRepository
import io.example.shared.eventstore.SharedEventStoreRepository
import io.example.shared.eventstore.SharedOutboxRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerService(
    private val repo: CustomerRepository,
    private val eventStore: SharedEventStoreRepository,
    private val outbox: SharedOutboxRepository
) {
    @Transactional
    fun createCustomer(name: String, email: String): Customer {
        val c = Customer(name = name, email = email)
        repo.save(c)
        eventStore.appendEvent(c.id.id, "CustomerCreated", mapOf("name" to name, "email" to email))
        outbox.saveOutboxEntry(c.id.id, "CustomerCreated", mapOf("name" to name, "email" to email))
        return c
    }
}