package com.snowcode.lab.dummy.java.template.service.impl;

import com.snowcode.lab.dummy.java.template.dao.DummyDao;
import com.snowcode.lab.dummy.java.template.dao.model.Dummy;
import com.snowcode.lab.dummy.java.template.service.DummyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DummyServiceImpl implements DummyService {

    private final IdGenerator idGenerator;
    private final DummyDao dummyDao;
//    private final DummyMessaging dummyMessaging; todo: SNO-14 - messaging module


    @Override
    public Optional<Dummy> findById(UUID id) {
        log.debug("Dummy is being searched by id:{}", id);
        var foundDummy = dummyDao.findById(id);
        log.debug("Dummy is found by id:{}, {}", id, foundDummy);
        return foundDummy;
    }

    @Override
    public Dummy create(Dummy dummy) {
        log.debug("Dummy is being created {}", dummy);
        var dummyToCreate = dummy.withId(idGenerator.generateId());
        var createdDummy = dummyDao.create(dummyToCreate);
//        dummyMessaging.sendCreateEvent(dummy); todo: SNO-14 - messaging module
        log.info("Dummy is created {}", createdDummy);
        return createdDummy;
    }
}
