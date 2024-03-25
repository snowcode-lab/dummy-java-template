package com.snowcode.lab.dummy.java.template.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.mockito.Mockito.mockStatic;

class DummyJavaTemplateApplicationTest {

    @Test
    void main_shouldRunSpringApplication() {
        try (var springAppMock = mockStatic(SpringApplication.class)) {
            DummyJavaTemplateApplication.main(new String[]{});
            springAppMock.verify(() -> SpringApplication.run(DummyJavaTemplateApplication.class, new String[]{}));
        }
    }
}