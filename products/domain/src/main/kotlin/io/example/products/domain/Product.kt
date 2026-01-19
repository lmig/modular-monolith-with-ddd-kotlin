package io.example.products.domain

import java.util.UUID
import java.time.Instant

data class ProductId(val id: UUID = UUID.randomUUID())

data class Product(
    val id: ProductId = ProductId(),
    var name: String,
    var price: Long,
    val createdAt: Instant = Instant.now()
)