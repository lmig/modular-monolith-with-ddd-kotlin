package io.example.orders.api.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class CreateOrderRequest(
    @field:NotNull
    @field:Min(1)
    val total: Long?
)