package com.snowcode.lab.dummy.java.template.service.impl;

import com.snowcode.lab.dummy.java.template.dao.DummyDao;
import com.snowcode.lab.dummy.java.template.dao.model.Dummy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.IdGenerator;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DummyServiceImplTest {

    @Mock
    private DummyDao dao;
    @Mock
    private IdGenerator idGenerator;

    @InjectMocks
    private DummyServiceImpl dummyService;

    @Test
    void findById() {
        var id = UUID.randomUUID();
        var expectedDummy = Dummy.builder().id(id).build();

        when(dao.findById(id)).thenReturn(Optional.of(expectedDummy));

        assertThat(dummyService.findById(id)).contains(expectedDummy);
    }

    @Test
    void findById_shouldReturnEmpty() {
        var id = UUID.randomUUID();

        when(dao.findById(id)).thenReturn(Optional.empty());

        assertThat(dummyService.findById(id)).isEmpty();
    }

    @Test
    void create() {
        var id = UUID.randomUUID();
        var expectedCreatedDummy = Dummy.builder().id(id).name("dummy-name").build();
        var dummyToCreate = expectedCreatedDummy.withId(null);

        when(idGenerator.generateId()).thenReturn(id);
        when(dao.create(expectedCreatedDummy)).thenReturn(expectedCreatedDummy);

        assertThat(dummyService.create(dummyToCreate)).isEqualTo(expectedCreatedDummy);
    }
}