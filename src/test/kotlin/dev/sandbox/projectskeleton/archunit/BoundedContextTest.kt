package dev.sandbox.projectskeleton.archunit

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import dev.sandbox.projectskeleton.ProjectSkeletonApplication

@AnalyzeClasses(packagesOf = [ProjectSkeletonApplication::class])
class BoundedContextTest {

    @ArchTest
    val notification_should_not_depend_on_order =
            ArchRuleDefinition.noClasses()
                    .that().resideInAPackage("..notification..")
                    .should().dependOnClassesThat().resideInAPackage("..order..")
                    .`as`("Notification BC should not depend on Order BC")

    @ArchTest
    val order_should_not_depend_on_notification =
            ArchRuleDefinition.noClasses()
                    .that().resideInAPackage("..order..")
                    .should().dependOnClassesThat().resideInAPackage("..notification..")
                    .`as`("Order BC should not depend on Notification BC")
}
