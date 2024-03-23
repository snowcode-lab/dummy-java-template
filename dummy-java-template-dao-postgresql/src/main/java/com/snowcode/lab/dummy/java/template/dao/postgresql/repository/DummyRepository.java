package com.snowcode.lab.dummy.java.template.dao.postgresql.repository;


import com.snowcode.lab.dummy.java.template.dao.postgresql.model.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DummyRepository extends JpaRepository<DummyEntity, UUID> {
}
