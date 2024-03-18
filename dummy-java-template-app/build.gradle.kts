plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":dummy-java-template-controller"))
    implementation(project(":dummy-java-template-service-impl"))
    implementation(project(":dummy-java-template-dao-postgresql"))

    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
