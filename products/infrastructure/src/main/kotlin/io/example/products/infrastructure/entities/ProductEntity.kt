package io.example.products.infrastructure.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id
    val id: UUID,
    var name: String,
    var price: Long,
    val createdAt: Instant
)