package io.example.orders.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.example.orders.api.dto.CreateOrderRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @WithMockUser(username = "admin", roles = ["ADMIN"])
    fun `create order endpoint returns created order`() {
        val req = CreateOrderRequest(total = 1000)
        mockMvc.post("/api/orders") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(req)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }
}