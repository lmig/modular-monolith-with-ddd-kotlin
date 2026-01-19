package io.example.billing.api.dto

import java.util.UUID
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class CreateInvoiceRequest(
    @field:NotNull
    val customerId: UUID?,
    @field:Min(0)
    val amount: Long
)