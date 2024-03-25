package com.snowcode.lab.dummy.java.template.dao.postgresql.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class AbstractPersistableTest {

    @Test
    void hashCodeAndEqualsTest() {
        var firstNewEntity = new TestEntity("new-entity");
        var secondNewEntity = new TestEntity("new-entity");
        var existedEntity = new TestEntity(UUID.randomUUID(), "existed-entity");
        var existedModifiedEntity = new TestEntity(existedEntity.getId(), "existed-entity-modified");

        var entitySet = new HashSet<AbstractPersistable<UUID>>();
        entitySet.add(firstNewEntity);
        entitySet.add(secondNewEntity);
        entitySet.add(existedEntity);
        entitySet.add(existedModifiedEntity);

        assertThat(firstNewEntity.isNew()).isTrue();
        assertThat(existedEntity.isNew()).isFalse();
        assertThat(firstNewEntity).isNotEqualTo(secondNewEntity);
        assertThat(firstNewEntity).isNotEqualTo(null);
        assertThat(firstNewEntity).isNotEqualTo(new AbstractPersistable<UUID>() {});

        assertThat(existedEntity).hasToString(existedModifiedEntity.toString());

        assertThat(entitySet).hasSize(3).containsExactlyInAnyOrder(firstNewEntity, secondNewEntity, existedEntity);
        assertThat(entitySet)
                .filteredOn(existedModifiedEntity::equals)
                .first()
                .usingRecursiveComparison()
                .isEqualTo(existedEntity);
    }

    @NoArgsConstructor
    @Getter
    private static class TestEntity extends AbstractPersistable<UUID> {

        private String name;

        public TestEntity(UUID id, String name) {
            this.name = name;
            setId(id);
        }

        public TestEntity(String name) {
            this.name = name;
        }

    }

}