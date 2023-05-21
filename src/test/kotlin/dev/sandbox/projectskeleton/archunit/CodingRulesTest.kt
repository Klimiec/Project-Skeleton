package dev.sandbox.projectskeleton.archunit

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*
import dev.sandbox.projectskeleton.ProjectSkeletonApplication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController


@AnalyzeClasses(packagesOf = [ProjectSkeletonApplication::class])
class CodingRulesTest {

    @ArchTest
    val domain_should_not_depend_on_spring_framework =
            noClasses()
                    .that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAPackage("org.springframework..")

    @ArchTest
    val rest_endpoints_must_reside_in_a_api_package =
            classes()
                    .that().areAnnotatedWith(RestController::class.java)
                    .should().resideInAnyPackage("..api..", "..web..", "..rest..")
                    .`as`("REST Endpoints should reside in a package  api/web/rest")

    @ArchTest
    val rest_controllers_must_have_names_ending_with_the_word_endpoint =
            classes()
                    .that().areAnnotatedWith(RestController::class.java)
                    .should().haveNameMatching(".*Endpoint")
                    .andShould().haveNameNotMatching(".*Controller.*")
                    .`as`("@RestController classes should have names ending with the word 'Endpoint'")

    @ArchTest
    val all_public_methods_in_the_rest_controller_should_return_response_entity =
            methods()
                    .that().areDeclaredInClassesThat().areAnnotatedWith(RestController::class.java)
                    .and().arePublic()
                    .should().haveRawReturnType(ResponseEntity::class.java)
                    .allowEmptyShould(true)
                    .`as`("REST endpoints should explicitly return response code")

    @ArchTest
    val interfaces_should_not_have_names_ending_with_the_er =
            noClasses()
                    .that().areInterfaces()
                    .should().haveNameMatching(".*er")
                    .allowEmptyShould(true)
                    .`as`("Interface name should capture 'intention of communication'. Avoid names: Sender, Fetcher, Publisher ")

    @ArchTest
    val interfaces_should_not_have_names_ending_the_word_interface =
            noClasses()
                    .that().areInterfaces()
                    .should().haveSimpleNameEndingWith("Interface")
                    .allowEmptyShould(true)

    @ArchTest
    val no_classes_should_have_names_ending_the_word_helper =
            noClasses()
                    .should().haveSimpleNameEndingWith("Helper")

    @ArchTest
    val no_classes_should_have_names_ending_the_word_impl =
            noClasses()
                    .should().haveSimpleNameEndingWith("Impl")

    @ArchTest
    val commands_should_not_be_used_in_domain =
            noClasses()
                    .that().haveSimpleName("Command")
                    .should().resideInAPackage("..domain..")
                    .allowEmptyShould(true)

    @ArchTest
    val no_classes_should_be_annotated_with_component =
            noClasses()
                    .should().beAnnotatedWith(Component::class.java)

    @ArchTest
    val no_classes_should_be_annotated_with_service =
            noClasses()
                    .should().beAnnotatedWith(Service::class.java)
}

