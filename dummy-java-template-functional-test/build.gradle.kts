dependencies {
    testImplementation(project(":dummy-java-template-app"))
    testImplementation(project(":dummy-java-template-controller-api"))

    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("io.rest-assured:rest-assured")
}