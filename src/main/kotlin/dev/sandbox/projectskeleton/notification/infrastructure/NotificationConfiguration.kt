package dev.sandbox.projectskeleton.notification.infrastructure

import dev.sandbox.projectskeleton.notification.application.SendNotification
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotificationConfiguration {

    @Bean
    fun sendNotification() = SendNotification()

}