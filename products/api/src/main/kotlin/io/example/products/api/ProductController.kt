package io.example.products.api

import io.example.products.application.ProductService
import io.example.products.api.dto.CreateProductRequest
import io.example.products.api.dto.ProductResponse
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/products")
class ProductController(private val service: ProductService) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateProductRequest): ProductResponse {
        val p = service.createProduct(req.name, req.price)
        return ProductResponse(p.id.id, p.name, p.price, p.createdAt)
    }
}