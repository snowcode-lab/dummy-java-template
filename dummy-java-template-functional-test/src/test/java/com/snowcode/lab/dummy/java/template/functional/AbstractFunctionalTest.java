package com.snowcode.lab.dummy.java.template.functional;

import com.snowcode.lab.dummy.java.template.app.DummyJavaTemplateApplication;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@EnableAutoConfiguration
@SpringBootTest(
        classes = DummyJavaTemplateApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan("com.snowcode.lab.dummy.java.template.functional.steps")
public abstract class AbstractFunctionalTest {

    @BeforeEach
    void restAssuredSetup(@LocalServerPort int localPort) {
        RestAssured.port = localPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
