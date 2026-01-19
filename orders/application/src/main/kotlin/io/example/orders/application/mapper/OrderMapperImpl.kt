package io.example.orders.application.mapper

import io.example.orders.api.dto.OrderResponse
import io.example.orders.domain.Order
import org.springframework.stereotype.Component

// Implementación manual (Kotlin idiomática) para evitar dependencia estricta en kapt/MapStruct durante desarrollo.
// Mantendremos la interfaz MapStruct por compatibilidad si quieres generar mappers posteriormente.
@Component
class OrderMapperImpl : OrderMapper {
    override fun toResponse(order: Order): OrderResponse {
        return OrderResponse(
            id = order.id.id,
            total = order.total,
            status = order.status.name
        )
    }
}