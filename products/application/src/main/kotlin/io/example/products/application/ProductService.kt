package io.example.products.application

import io.example.products.domain.Product
import io.example.products.infrastructure.ProductRepository
import io.example.shared.eventstore.SharedEventStoreRepository
import io.example.shared.eventstore.SharedOutboxRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val repo: ProductRepository,
    private val eventStore: SharedEventStoreRepository,
    private val outbox: SharedOutboxRepository
) {
    @Transactional
    fun createProduct(name: String, price: Long): Product {
        val p = Product(name = name, price = price)
        repo.save(p)
        eventStore.appendEvent(p.id.id, "ProductCreated", mapOf("name" to name, "price" to price))
        outbox.saveOutboxEntry(p.id.id, "ProductCreated", mapOf("name" to name, "price" to price))
        return p
    }
}