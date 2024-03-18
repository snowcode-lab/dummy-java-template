package com.snowcode.lab.dummy.java.template.dao.postgresql;

import com.snowcode.lab.dummy.java.template.dao.DummyDao;
import com.snowcode.lab.dummy.java.template.dao.model.Dummy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DummyPostgresqlDaoIntegrationTest extends AbstractPostgresqlIntegrationTest {

    @Autowired
    private DummyDao dummyDao;

    @Test
    void create_shouldBeSuccessfulAndFound() {
        var dummyId = UUID.randomUUID();
        var dummy = Dummy.builder()
                .id(dummyId)
                .name("test-name")
                .build();

        assertThat(dummyDao.create(dummy)).isEqualTo(dummy);
        assertThat(dummyDao.findById(dummyId)).contains(dummy);
    }

    @Test
    void findById_shouldReturnEmptyResponse() {
        assertThat(dummyDao.findById(UUID.randomUUID())).isEmpty();
    }

}