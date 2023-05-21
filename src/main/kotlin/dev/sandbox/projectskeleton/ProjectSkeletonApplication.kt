package dev.sandbox.projectskeleton

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProjectSkeletonApplication

fun main(args: Array<String>) {
    runApplication<ProjectSkeletonApplication>(*args)
}
