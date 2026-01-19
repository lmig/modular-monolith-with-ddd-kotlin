package io.example.orders.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["io.example.orders"])
@EnableScheduling
class OrdersApplication

fun main(args: Array<String>) {
    runApplication<OrdersApplication>(*args)
}