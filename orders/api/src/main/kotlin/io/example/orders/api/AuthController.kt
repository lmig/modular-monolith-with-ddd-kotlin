package io.example.orders.api

import io.example.orders.api.config.JwtTokenProvider
import io.example.orders.api.dto.AuthRequest
import io.example.orders.api.dto.AuthResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val tokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
) {
    @PostMapping("/login")
    fun login(@RequestBody req: AuthRequest): ResponseEntity<AuthResponse> {
        val auth = UsernamePasswordAuthenticationToken(req.username, req.password)
        authenticationManager.authenticate(auth)
        val userDetails = userDetailsService.loadUserByUsername(req.username)
        val roles = userDetails.authorities.map { it.authority }
        val token = tokenProvider.createToken(req.username, roles)
        return ResponseEntity.ok(AuthResponse(token))
    }
}