package io.example.customers.api

import io.example.customers.application.CustomerService
import io.example.customers.application.mapper.CustomerMapper
import io.example.customers.api.dto.CreateCustomerRequest
import io.example.customers.api.dto.CustomerResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomersController(private val service: CustomerService, private val mapper: CustomerMapper) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateCustomerRequest): ResponseEntity<CustomerResponse> {
        val c = service.createCustomer(req.name, req.email)
        return ResponseEntity.ok(mapper.toResponse(c))
    }
}