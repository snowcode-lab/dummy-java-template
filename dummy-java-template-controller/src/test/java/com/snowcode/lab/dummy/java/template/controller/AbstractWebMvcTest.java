package com.snowcode.lab.dummy.java.template.controller;

import com.snowcode.lab.dummy.java.template.controller.configuration.DummyControllerAutoConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest(classes = DummyControllerAutoConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractWebMvcTest {

    @LocalServerPort
    private int localServerPort;

    @BeforeEach
    void restAssuredSetup() {
        RestAssured.port = localServerPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @TestConfiguration
    @EnableAutoConfiguration
    @SpringBootConfiguration
    public static class MvcTestConfiguration {

    }

}
