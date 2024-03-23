dependencies {
    implementation(project(":dummy-java-template-controller-api"))
    implementation(project(":dummy-java-template-service"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")


    testImplementation("io.rest-assured:rest-assured")
}