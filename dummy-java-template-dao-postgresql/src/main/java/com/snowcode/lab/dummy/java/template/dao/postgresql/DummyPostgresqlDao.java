package com.snowcode.lab.dummy.java.template.dao.postgresql;

import com.snowcode.lab.dummy.java.template.dao.DummyDao;
import com.snowcode.lab.dummy.java.template.dao.model.Dummy;
import com.snowcode.lab.dummy.java.template.dao.postgresql.mapper.DummyDaoMapper;
import com.snowcode.lab.dummy.java.template.dao.postgresql.repository.DummyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DummyPostgresqlDao implements DummyDao {

    private final DummyDaoMapper dummyDaoMapper;
    private final DummyRepository dummyRepository;

    @Override
    public Optional<Dummy> findById(UUID id) {
        log.debug("Dummy is being searched by id:{}", id);
        var foundDummy = dummyRepository.findById(id).map(dummyDaoMapper::convertToModel);
        log.debug("Dummy is found by id:{}, {}", id, foundDummy);
        return foundDummy;
    }

    @Override
    public Dummy create(Dummy dummy) {
        log.debug("Dummy is being created {}", dummy);
        var entity = dummyDaoMapper.convertToEntity(dummy);
        var createdEntity = dummyRepository.save(entity);
        var createdDummy = dummyDaoMapper.convertToModel(createdEntity);
        log.debug("Dummy is created {}", dummy);
        return createdDummy;
    }
}
