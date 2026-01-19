package io.example.orders.api.config

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

// Servicio simple en memoria que utiliza PasswordEncoder para el password. Reemplázalo por JPA cuando quieras usuarios reales.
@Service
class DbUserDetailsService(private val passwordEncoder: PasswordEncoder) : UserDetailsService {

    // Inicialmente un solo usuario admin; sustituir por persistencia
    private val users = mapOf(
        "admin" to passwordEncoder.encode("admin"),
        "user" to passwordEncoder.encode("password")
    )

    override fun loadUserByUsername(username: String): UserDetails {
        val pw = users[username] ?: throw UsernameNotFoundException("User $username not found")
        return User.withUsername(username).password(pw).roles(if (username == "admin") "ADMIN" else "USER").build()
    }
}