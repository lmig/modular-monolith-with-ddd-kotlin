package io.example.orders.application.mapper

import io.example.orders.domain.Order
import io.example.orders.domain.OrderId
import io.example.orders.domain.OrderStatus
import io.example.orders.api.dto.OrderResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.util.UUID

@Mapper(componentModel = "spring")
interface OrderMapper {
    fun toResponse(order: Order): OrderResponse
}