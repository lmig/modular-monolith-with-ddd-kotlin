package io.example.customers.infrastructure

import io.example.customers.domain.Customer
import io.example.customers.domain.CustomerId
import io.example.customers.domain.CustomerId as CID
import io.example.customers.domain.Customer as CD
import io.example.customers.infrastructure.entities.CustomerEntity
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CustomerRepository(private val em: EntityManager) {
    fun save(customer: Customer) {
        val entity = CustomerEntity(customer.id.id, customer.name, customer.email, customer.createdAt)
        em.merge(entity)
    }

    fun findById(id: UUID): Customer? {
        val e = em.find(CustomerEntity::class.java, id) ?: return null
        return Customer(CID(e.id), e.name, e.email, e.createdAt)
    }
}