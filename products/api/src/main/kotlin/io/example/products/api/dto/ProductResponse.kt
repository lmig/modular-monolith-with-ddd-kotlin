package io.example.products.api.dto

import java.util.UUID
import java.time.Instant

data class ProductResponse(val id: UUID, val name: String, val price: Long, val createdAt: Instant)