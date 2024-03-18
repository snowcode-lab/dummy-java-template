package com.snowcode.lab.dummy.java.template.controller;

import com.snowcode.lab.dummy.java.template.dao.model.Dummy;
import com.snowcode.lab.dummy.java.template.service.DummyService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static com.snowcode.lab.dummy.java.template.controller.util.JsonMatchers.jsonEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DummyControllerWebMvcTest extends AbstractWebMvcTest {

    @MockBean
    private DummyService dummyService;

    @Test
    void findDummyById_shouldReturnOk() {
        var id = UUID.fromString("3b5af130-da74-4643-a03b-d4a9fd091703");
        var dummy = Dummy.builder()
                .id(id)
                .name("expectedDummy-name")
                .build();
        when(dummyService.findById(id)).thenReturn(Optional.of(dummy));

        given().get("/dummy/{id}", id)
                .then()
                .statusCode(200)
                .body(jsonEquals("""
                        {
                          "id": "3b5af130-da74-4643-a03b-d4a9fd091703",
                          "name": "expectedDummy-name"
                        }""", true));
    }

    @Test
    void findDummyById_shouldReturnBadRequest() {
        given().pathParam("id", "invalid-uuid")
                .when().get("/dummy/{id}")
                .then()
                .statusCode(400)
                .body(jsonEquals("""
                        {
                          "status": 400,
                          "error": "Bad Request",
                          "message": "Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; Invalid UUID string: invalid-uuid"
                        }""", false));
    }

    @Test
    void createDummy_shouldReturnOk() {
        var dummy = Dummy.builder()
                .id(UUID.fromString("3b5af130-da74-4643-a03b-d4a9fd091703"))
                .name("expectedDummy-name")
                .build();
        when(dummyService.create(dummy.withId(null))).thenReturn(dummy);


        given().contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "expectedDummy-name"
                        }""")
                .when().post("/dummy")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(jsonEquals("""
                        {
                          "id": "3b5af130-da74-4643-a03b-d4a9fd091703",
                          "name": "expectedDummy-name"
                        }""", true));
    }

    @Test
    void createDummy_shouldReturnBadRequest() {
        given().contentType(ContentType.JSON)
                .body("""
                        {
                          "name": ""
                        }""")
                .when().post("/dummy")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("timestamp", not(blankOrNullString()))
                .body(jsonEquals("""
                        {
                          "error": "Bad Request",
                          "message": "Validation failed for object='dummyCreateRequest'. Error count: 1",
                          "errors": [
                            {
                              "defaultMessage": "must not be empty",
                              "objectName": "dummyCreateRequest",
                              "field": "name",
                              "rejectedValue": "",
                              "code": "NotEmpty"
                            }
                          ],
                          "path": "/dummy"
                        }""", false));
    }

    @Test
    void createDummy_shouldReturnServerError() {
        when(dummyService.create(any())).thenThrow(new IllegalStateException("internal custom server error"));


        given().contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "expectedDummy-name"
                        }""")
                .when().post("/dummy")
                .then()
                .statusCode(500)
                .contentType(ContentType.JSON)
                .body("timestamp", not(blankOrNullString()))
                .body(jsonEquals("""
                        {
                          "status": 500,
                          "error": "Internal Server Error",
                          "message": "internal custom server error",
                          "path": "/dummy"
                        }""", false));
    }

}