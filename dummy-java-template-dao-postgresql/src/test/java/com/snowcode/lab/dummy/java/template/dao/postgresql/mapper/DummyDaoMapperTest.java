package com.snowcode.lab.dummy.java.template.dao.postgresql.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class DummyDaoMapperTest {

    private final DummyDaoMapper mapper = Mappers.getMapper(DummyDaoMapper.class);

    @Test
    void convertToModel_shouldConvertNull() {
        assertThat(mapper.convertToModel(null)).isNull();
    }

    @Test
    void convertToEntity_shouldConvertNull() {
        assertThat(mapper.convertToEntity(null)).isNull();
    }
}