import org.gradle.api.tasks.testing.logging.TestLogEvent;

plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.4"
    id("org.springframework.boot") version "3.2.3" apply false
    id("jacoco")
}

extra["springBootVersion"] = "3.2.3"
extra["springCloudVersion"] = "2023.0.0"
extra["mapstructVersion"] = "1.5.5.Final"


allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "com.snowcode.lab"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "jacoco")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        compileOnly("org.mapstruct:mapstruct:${property("mapstructVersion")}")

        annotationProcessor("org.projectlombok:lombok")
        annotationProcessor("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")

        testCompileOnly("org.projectlombok:lombok")

        testAnnotationProcessor("org.projectlombok:lombok")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.mapstruct:mapstruct:${property("mapstructVersion")}")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks {
        compileJava {
            options.compilerArgs.add("-Amapstruct.defaultComponentModel=spring")
            options.compilerArgs.add("-Amapstruct.unmappedTargetPolicy=IGNORE")
        }

        test {
            useJUnitPlatform()
            testLogging { events = setOf(TestLogEvent.FAILED) }

            reports {
                html.apply { isEnabled = true }
                junitXml.apply { isEnabled = true }
            }

            finalizedBy(jacocoTestReport)
        }

        jacocoTestReport {
            dependsOn(test)

            additionalSourceDirs.setFrom(files(sourceSets.main.get().allSource.srcDirs))
            sourceDirectories.setFrom(files(sourceSets.main.get().allSource.srcDirs))
            classDirectories.setFrom(files(sourceSets.main.get().output))
            executionData.setFrom(files(file("build/jacoco/test.exec")))

            reports {
                html.required = true
            }
        }
    }
}

tasks {
    jar { enabled = false }

    test { finalizedBy(jacocoTestReport) }
    jacocoTestReport {
        dependsOn(subprojects.map { "${it.path}:test" })
        additionalSourceDirs.setFrom(files(subprojects.flatMap { it.sourceSets.main.get().allSource.srcDirs }))
        sourceDirectories.setFrom(files(subprojects.flatMap { it.sourceSets.main.get().allSource.srcDirs }))
        classDirectories.setFrom(files(subprojects.flatMap { it.sourceSets.main.get().output }))
        executionData.setFrom(files(subprojects.map { it.file("build/jacoco/test.exec") }))

        reports {
            html.required = true
            csv.required = true
        }
    }

}