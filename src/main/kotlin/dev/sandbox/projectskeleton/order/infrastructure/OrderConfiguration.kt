package dev.sandbox.projectskeleton.order.infrastructure

import dev.sandbox.projectskeleton.order.application.PlaceOrder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderConfiguration {

    @Bean
    fun createOrder() = PlaceOrder()

}