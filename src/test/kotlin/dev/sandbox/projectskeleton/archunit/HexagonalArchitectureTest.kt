package dev.sandbox.projectskeleton.archunit

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*
import dev.sandbox.projectskeleton.ProjectSkeletonApplication

@AnalyzeClasses(packagesOf = [ProjectSkeletonApplication::class])
class HexagonalArchitectureTest {

    @ArchTest
    val domain_should_not_depend_on_other_layers =
            noClasses()
                    .that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAnyPackage("..infrastructure..", "..application..", "..api..")

    /*
     No dependency on API layer forces developers to map request objects into command/query objects defined by application layers.
     Request objects consist of primitive types that we want to avoid in the application/domain in favor of Value Objects.
     No dependency on the infrastructure layer forces developers to use 'communication ports' defined by the domain layer.
     */
    @ArchTest
    val application_should_not_depend_on_api_or_infrastructure =
            noClasses()
                    .that().resideInAPackage("..application..")
                    .should().dependOnClassesThat().resideInAnyPackage("..api..", "..infrastructure..")

    /*
    No dependency on the infrastructure layer forces developers to use 'communication ports' defined by the domain layer.
    Infrastructure layer is the most outer layer and should not be used by other layers.
     */
    @ArchTest
    val api_should_not_depend_on_infrastructure =
            noClasses()
                    .that().resideInAPackage("..api..")
                    .should().dependOnClassesThat().resideInAPackage("..infrastructure..")

    @ArchTest
    val infrastructure_should_not_depend_on_api =
            noClasses()
                    .that().resideInAPackage("..infrastructure..")
                    .should().dependOnClassesThat().resideInAPackage("..api..")
}
