package com.snowcode.lab.dummy.java.template.service;

import com.snowcode.lab.dummy.java.template.dao.model.Dummy;

import java.util.Optional;
import java.util.UUID;

public interface DummyService {

    Optional<Dummy> findById(UUID id);

    /** It creates a dummy and send an event about the creation */
    Dummy create(Dummy dummy);


}
