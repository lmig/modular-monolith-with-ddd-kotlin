package io.example.products.infrastructure

import io.example.products.domain.Product
import io.example.products.domain.ProductId
import io.example.products.infrastructure.entities.ProductEntity
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ProductRepository(private val em: EntityManager) {
    fun save(product: Product) {
        val e = ProductEntity(product.id.id, product.name, product.price, product.createdAt)
        em.merge(e)
    }

    fun findById(id: UUID): Product? {
        val e = em.find(ProductEntity::class.java, id) ?: return null
        return Product(ProductId(e.id), e.name, e.price, e.createdAt)
    }
}