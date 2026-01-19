package io.example.orders.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.example.orders.api.dto.CreateOrderRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class OrdersTestcontainersIntegration {

    companion object {
        @Container
        val postgres = PostgreSQLContainer<Nothing>("postgres:15").apply {
            withDatabaseName("appdb")
            withUsername("app")
            withPassword("pass")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(reg: DynamicPropertyRegistry) {
            reg.add("spring.datasource.url") { postgres.jdbcUrl }
            reg.add("spring.datasource.username") { postgres.username }
            reg.add("spring.datasource.password") { postgres.password }
        }
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @WithMockUser(username = "admin", roles = ["ADMIN"])
    fun `create order e2e`() {
        val req = CreateOrderRequest(total = 500)
        val mvcResult = mockMvc.post("/api/orders") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(req)
        }.andReturn()

        assertEquals(200, mvcResult.response.status)
        assert(mvcResult.response.contentAsString.contains("\"total\":500"))
    }
}