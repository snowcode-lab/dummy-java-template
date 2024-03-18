package com.snowcode.lab.dummy.java.template.functional;

import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyCreateRequest;
import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyResponse;
import com.snowcode.lab.dummy.java.template.functional.steps.DummySteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DummyFunctionalTest extends AbstractFunctionalTest {

    @Autowired
    private DummySteps dummySteps;

    @Test
    void shouldCreateAndGetDummy() {
        var expectedDummyResponse = DummyResponse.builder()
                .name("dummy-name")
                .build();

        var dummyCreateRequest = DummyCreateRequest.builder()
                .name("dummy-name")
                .build();

        var dummyResponse = dummySteps.createDummy(dummyCreateRequest).assertThat()
                .statusCode(200)
                .extract().as(DummyResponse.class);

        assertThat(dummyResponse).hasNoNullFieldsOrProperties()
                .usingRecursiveComparison().ignoringFields("id")
                .isEqualTo(expectedDummyResponse);

        var foundDummyResponse = dummySteps.findDummy(dummyResponse.id())
                .assertThat()
                .statusCode(200)
                .extract().as(DummyResponse.class);

        assertThat(foundDummyResponse).isEqualTo(dummyResponse);
    }

}
