package com.snowcode.lab.dummy.java.template.dao.postgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dummy")
@SuperBuilder
@NoArgsConstructor
public class DummyEntity extends AbstractPersistable<UUID> {

    private String name;

}
