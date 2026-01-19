package io.example.orders.api.dto

data class AuthResponse(
    val token: String,
    val tokenType: String = "Bearer"
)