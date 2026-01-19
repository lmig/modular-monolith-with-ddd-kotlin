package io.example.customers.application.mapper

import io.example.customers.domain.Customer
import io.example.customers.api.dto.CustomerResponse
import org.springframework.stereotype.Component

@Component
class CustomerMapper {
    fun toResponse(c: Customer): CustomerResponse = CustomerResponse(c.id.id, c.name, c.email, c.createdAt)
}