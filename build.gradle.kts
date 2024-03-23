import org.gradle.api.tasks.testing.logging.TestLogEvent;

plugins {
    id("java-library")
    id("io.spring.dependency-management") version "1.1.4"
    id("org.springframework.boot") version "3.2.3" apply false
}

extra["springBootVersion"] = "3.2.3"
extra["springCloudVersion"] = "2023.0.0"
extra["mapstructVersion"] = "1.5.5.Final"

subprojects {

    group = "com.snowcode.lab"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        mavenCentral()
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
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")


        testAnnotationProcessor("org.projectlombok:lombok")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
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
            testLogging {
                events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
            }

            reports {
                html.apply { isEnabled = true }
                junitXml.apply { isEnabled = true }
            }

        }

    }

}

