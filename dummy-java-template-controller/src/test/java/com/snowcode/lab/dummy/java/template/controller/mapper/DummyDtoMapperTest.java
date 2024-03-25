package com.snowcode.lab.dummy.java.template.controller.mapper;

import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyCreateRequest;
import com.snowcode.lab.dummy.java.template.controller.api.dto.DummyResponse;
import com.snowcode.lab.dummy.java.template.dao.model.Dummy;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DummyDtoMapperTest {
    private final DummyDtoMapper mapper = Mappers.getMapper(DummyDtoMapper.class);

    @Test
    void convertToModel() {
        assertThat(mapper.convertToModel(TestData.DUMMY_CREATE_REQUEST))
                .isEqualTo(TestData.DUMMY.withId(null));
    }

    @Test
    void convertToModel_shouldConvertNull() {
        assertThat(mapper.convertToModel(null)).isNull();
    }

    @Test
    void convertToDummyResponse() {
        assertThat(mapper.convertToDummyResponse(TestData.DUMMY))
                .isEqualTo(TestData.DUMMY_RESPONSE);
    }

    @Test
    void convertToDummyResponse_shouldConvertNull() {
        assertThat(mapper.convertToDummyResponse(null)).isNull();
    }

    static class TestData {
        private static final UUID ID = UUID.randomUUID();
        private static final String NAME = "test-name";

        private static final Dummy DUMMY = Dummy.builder()
                .id(ID)
                .name(NAME)
                .build();

        private static final DummyCreateRequest DUMMY_CREATE_REQUEST = DummyCreateRequest.builder()
                .name(NAME)
                .build();

        private static final DummyResponse DUMMY_RESPONSE = DummyResponse.builder()
                .id(ID)
                .name(NAME)
                .build();
    }

}