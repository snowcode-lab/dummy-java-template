package com.snowcode.lab.dummy.java.template.functional.steps;

import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyCreateRequest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.boot.test.context.TestComponent;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@TestComponent
public class DummySteps {

    public ValidatableResponse createDummy(DummyCreateRequest request) {
        return given().contentType(ContentType.JSON)
                .body(request)
                .when().post("/dummy")
                .then();
    }

    public ValidatableResponse findDummy(UUID id) {
        return given().pathParam("id", id)
                .when().get("/dummy/{id}")
                .then();
    }

}
