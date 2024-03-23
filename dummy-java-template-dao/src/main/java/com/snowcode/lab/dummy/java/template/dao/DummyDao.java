package com.snowcode.lab.dummy.java.template.dao;

import com.snowcode.lab.dummy.java.template.dao.model.Dummy;

import java.util.Optional;
import java.util.UUID;

public interface DummyDao {

    Optional<Dummy> findById(UUID id);

    Dummy create(Dummy dummy);


}
