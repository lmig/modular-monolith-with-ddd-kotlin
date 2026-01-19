package io.example.orders.api

import io.example.orders.application.OrderService
import io.example.orders.application.mapper.OrderMapper
import io.example.orders.api.dto.CreateOrderRequest
import io.example.orders.api.dto.OrderResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService, private val mapper: OrderMapper) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateOrderRequest): OrderResponse {
        val order = orderService.createOrder(req.total!!)
        return mapper.toResponse(order)
    }

    @PostMapping("/{id}/pay")
    fun pay(@PathVariable id: UUID) {
        orderService.markPaid(id)
    }
}