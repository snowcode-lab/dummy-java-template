package com.snowcode.lab.dummy.java.template.controller.api.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DummyCreateRequestTest {

    private static final DummyCreateRequest VALID_DUMMY = DummyCreateRequest.builder().name("dummy-name").build();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void validation_shouldBySuccessful() {
        assertThat(validator.validate(VALID_DUMMY)).isEmpty();
    }

    @Test
    void validation_shouldByFailed_whenNameIsEmpty() {
        var expectedMessage = "must not be empty";
        var expectedPropertyPath = "name";

        var dummy = VALID_DUMMY.withName("");

        validateDummyOnOneError(dummy, expectedMessage, expectedPropertyPath);
    }

    @Test
    void validation_shouldByFailed_whenNameIsNull() {
        var expectedMessage = "must not be empty";
        var expectedPropertyPath = "name";

        var dummy = VALID_DUMMY.withName(null);

        validateDummyOnOneError(dummy, expectedMessage, expectedPropertyPath);
    }

    private void validateDummyOnOneError(DummyCreateRequest dummy, String expectedMessage, String expectedPropertyPath) {
        assertThat(validator.validate(dummy))
                .hasSize(1)
                .first()
                .returns(expectedMessage, ConstraintViolation::getMessage)
                .returns(expectedPropertyPath, dummyCreateRequestConstraintViolation -> dummyCreateRequestConstraintViolation.getPropertyPath().toString());
    }

}